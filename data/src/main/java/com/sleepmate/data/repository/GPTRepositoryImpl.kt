package com.sleepmate.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.sleepmate.data.api.GPTApiService
import com.sleepmate.data.datasource.local.OnboardingPreferences
import com.sleepmate.data.model.GPTMessage
import com.sleepmate.data.model.GPTRequest
import com.sleepmate.domain.repository.DailyHealthMetricsRepository
import com.sleepmate.domain.repository.GPTRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GPTRepositoryImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
    private val onboardingPreferences: OnboardingPreferences,
    private val healthMetricsRepository: DailyHealthMetricsRepository
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

            // 1. LEEMOS PREFERENCIAS DEL USUARIO
            val userProfile = onboardingPreferences.userProfile.first()

            // 2. OBTENEMOS DATOS DE SALUD REALES (Últimos 7 días) - FASE 4
            val recentMetrics = healthMetricsRepository.getRecentMetrics(7).first()

            // 3. CONSTRUIMOS EL INFORME DE SALUD PARA LA IA
            val healthContext = StringBuilder().apply {
                append("\n--- INFORME DE SALUD REAL (Últimos 7 días) ---\n")
                if (recentMetrics.isEmpty()) {
                    append("No hay datos históricos disponibles todavía.\n")
                } else {
                    recentMetrics.forEach { m ->
                        val hours = m.sleepDurationMinutes / 60
                        val mins = m.sleepDurationMinutes % 60
                        append("Fecha: ${m.date} | Sueño: ${hours}h ${mins}min | Pasos: ${m.steps}")
                        
                        // Incluimos peso y altura si están disponibles en la métrica diaria
                        m.weight?.let { append(" | Peso: $it kg") }
                        m.height?.let { append(" | Altura: $it m") }
                        
                        m.avgHeartRate?.let { append(" | FC Media: $it bpm") }
                        m.hrvRmssd?.let { append(" | HRV: ${it.toInt()}ms") }
                        
                        // Identificación del Origen de Datos para la IA
                        m.sourceApp?.let { append(" | Fuente: $it") }
                        append("\n")
                    }
                }
            }.toString()

            val userName = if (userProfile.name.isNotEmpty()) userProfile.name else "el usuario"
            val weightStr = userProfile.weight?.let { "$it kg" } ?: "No especificado"
            val heightStr = userProfile.height?.let { "$it m" } ?: "No especificado"

            val userContext = """
                
                --- DATOS DEL USUARIO ACTUAL (Contexto para personalizar la respuesta) ---
                Estás hablando con: $userName.
                Rango de edad: ${userProfile.ageRange}
                Peso: $weightStr
                Altura: $heightStr
                Nivel de estrés reportado (1-5): ${userProfile.stressLevel}
                Consumo de estimulantes: ${userProfile.stimulantConsumption}
                Sus metas principales son: ${userProfile.goals.joinToString(", ")}
                
                $healthContext
                
                INSTRUCCIÓN DE PERSONALIZACIÓN:
                  Usa los datos de perfil (incluyendo peso y altura) y los datos de salud reales para dar un consejo más cercano.
                 Si ves datos provenientes de apps específicas (como Google Fit, Samsung Health u otros) en el informe de salud, menciona que los has obtenido de allí.
                 Ten en cuenta su composición física (como el peso y la altura de la fuente sincronizada) si es relevante para el sueño.
               SIEMPRE respetando las limitaciones médicas y de seguridad definidas al principio.
            """.trimIndent()

            // 4. CONCATENAMOS: REGLAS FIJAS + CONTEXTO DINÁMICO
            val finalSystemPrompt = SYSTEM_PROMPT + userContext

            // Preparamos los mensajes para GPT
            val messages = listOf(
                GPTMessage(role = "system", content = finalSystemPrompt),
                GPTMessage(role = "user", content = userMessage)
            )

            val request = GPTRequest(
                model = "gpt-4.1-mini",
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

            throw Exception("No pude procesar tu consulta. Intenta nuevamente más tarde.")        }
    }
}
