package com.sleepmate.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.sleepmate.data.api.GPTApiService
import com.sleepmate.data.datasource.local.OnboardingPreferences // <--- IMPORTANTE: Tu paquete correcto
import com.sleepmate.data.model.GPTMessage
import com.sleepmate.data.model.GPTRequest
import com.sleepmate.domain.repository.GPTRepository
import kotlinx.coroutines.flow.first // <--- IMPORTANTE: Para leer el Flow una sola vez
import kotlinx.coroutines.tasks.await
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GPTRepositoryImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
    private val onboardingPreferences: OnboardingPreferences // <--- 1. INYECTAMOS TU CLASE
) : GPTRepository {

    companion object {
        private const val GPT_API_KEY_CONFIG = "gpt_api_key"
        private const val GPT_BASE_URL = "https://api.openai.com/"

        // --- TUS REGLAS ORIGINALES SE MANTIENEN INTACTAS ---
        private const val SYSTEM_PROMPT = """
Eres SleepMate, un asistente especializado en salud del sueño y descanso. 

INSTRUCCIONES IMPORTANTES:
- Solo responde preguntas relacionadas con sueño, descanso, higiene del sueño, insomnio, rutinas nocturnas y temas similares
- Si la pregunta no está relacionada con el sueño, responde cortésmente que solo puedes ayudar con temas de sueño y descanso
- NO proporciones diagnósticos médicos específicos
- NO recomiendes medicamentos específicos
- Si detectas un problema serio de salud, recomienda consultar con un profesional médico
- Mantén tus respuestas en español
- Sé empático, comprensivo y útil
- Limita tus respuestas a máximo 150 palabras
- Proporciona consejos prácticos y basados en evidencia científica

Ejemplos de lo que SÍ puedes responder:
- Técnicas de relajación para dormir mejor
- Rutinas de higiene del sueño
- Consejos sobre el ambiente del dormitorio
- Hábitos que afectan el sueño
- Técnicas de respiración para relajarse

Ejemplos de lo que NO debes responder:
- Preguntas sobre otros temas de salud no relacionados con el sueño
- Diagnósticos médicos específicos
- Recomendaciones de medicamentos
- Temas que no están relacionados con el descanso
"""
    }

    private val gptApiService: GPTApiService by lazy {
        Retrofit.Builder()
            .baseUrl(GPT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GPTApiService::class.java)
    }

    override suspend fun getGPTApiKey(): String? {
        return try {
            remoteConfig.fetchAndActivate().await()
            val apiKey = remoteConfig.getString(GPT_API_KEY_CONFIG)

            if (apiKey.isBlank()) {
                Timber.w("GPT API Key is empty in Remote Config")
                return null
            }
            Timber.d("GPT API Key retrieved successfully")
            apiKey
        } catch (e: Exception) {
            Timber.e(e, "Error retrieving GPT API Key")
            null
        }
    }

    override suspend fun sendSleepHealthQuery(userMessage: String): String {
        try {
            val apiKey = getGPTApiKey()
                ?: throw Exception("No se pudo obtener la clave API de GPT")

            // <--- 2. LEEMOS TUS PREFERENCIAS GUARDADAS --->
            // .first() suspende la corrutina hasta obtener el valor actual del UserProfile
            val userProfile = onboardingPreferences.userProfile.first()

            // <--- 3. CREAMOS EL CONTEXTO DINÁMICO --->
            val userName = if (userProfile.name.isNotEmpty()) userProfile.name else "el usuario"

            val userContext = """
                
                --- DATOS DEL USUARIO ACTUAL (Contexto para personalizar la respuesta) ---
                Estás hablando con: $userName.
                Rango de edad: ${userProfile.ageRange}
                Nivel de estrés reportado (1-5): ${userProfile.stressLevel}
                Consumo de estimulantes: ${userProfile.stimulantConsumption}
                Sus metas principales son: ${userProfile.goals.joinToString(", ")}
                
                INSTRUCCIÓN DE PERSONALIZACIÓN:
                Usa los datos de arriba para dar un consejo más cercano y personalizado, pero SIEMPRE respetando las limitaciones médicas y de seguridad definidas al principio.
            """.trimIndent()

            // <--- 4. CONCATENAMOS: REGLAS FIJAS + CONTEXTO DINÁMICO --->
            val finalSystemPrompt = SYSTEM_PROMPT + userContext

            // Preparamos los mensajes para GPT
            val messages = listOf(
                GPTMessage(role = "system", content = finalSystemPrompt),
                GPTMessage(role = "user", content = userMessage)
            )

            val request = GPTRequest(
                model = "gpt-4.1-mini", // <--- CORRECCIÓN: Usar gpt-4o-mini o gpt-3.5-turbo (gpt-4.1 no existe)
                messages = messages,
                maxTokens = 500,
                temperature = 0.7
            )

            val response = gptApiService.sendMessage(
                authorization = "Bearer $apiKey",
                request = request
            )

            if (!response.isSuccessful) {
                Timber.e("GPT API call failed with code: ${response.code()}")
                throw Exception("Error en la respuesta del servidor: ${response.code()}")
            }

            val gptResponse = response.body()

            if (gptResponse?.error != null) {
                Timber.e("GPT API returned error: ${gptResponse.error.message}")
                throw Exception("Error de la IA: ${gptResponse.error.message}")
            }

            val aiMessage = gptResponse?.choices?.firstOrNull()?.message?.content

            if (aiMessage.isNullOrBlank()) {
                throw Exception("La respuesta de la IA está vacía")
            }

            Timber.d("GPT response received successfully")
            return aiMessage.trim()

        } catch (e: Exception) {
            Timber.e(e, "Error sending message to GPT")

            val errorMessage = when {
                e.message?.contains("network", ignoreCase = true) == true ->
                    "Error de conexión. Verifica tu internet e intenta nuevamente."
                e.message?.contains("timeout", ignoreCase = true) == true ->
                    "La consulta tardó demasiado. Intenta nuevamente."
                e.message?.contains("API", ignoreCase = true) == true ->
                    "Servicio temporalmente no disponible. Intenta más tarde."
                else ->
                    "No pude procesar tu consulta. Intenta nuevamente más tarde."
            }

            throw Exception(errorMessage)
        }
    }
}
