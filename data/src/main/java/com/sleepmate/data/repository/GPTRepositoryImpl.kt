package com.sleepmate.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.sleepmate.data.api.GPTApiService
import com.sleepmate.data.model.GPTMessage
import com.sleepmate.data.model.GPTRequest
import com.sleepmate.domain.repository.GPTRepository
import kotlinx.coroutines.tasks.await
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GPTRepositoryImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) : GPTRepository {
    
    companion object {
        private const val GPT_API_KEY_CONFIG = "gpt_api_key"
        private const val GPT_BASE_URL = "https://api.openai.com/"
        
        // Prompt del sistema en español que limita las respuestas a salud del sueño
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
            // Activamos la configuración remota
            remoteConfig.fetchAndActivate().await()
            
            val apiKey = remoteConfig.getString(GPT_API_KEY_CONFIG)
            
            if (apiKey.isBlank()) {
                Timber.w("GPT API Key is empty in Remote Config")
                return null
            }
            
            Timber.d("GPT API Key retrieved successfully from Remote Config")
            apiKey
            
        } catch (e: Exception) {
            Timber.e(e, "Error retrieving GPT API Key from Remote Config")
            null
        }
    }
    
    override suspend fun sendSleepHealthQuery(userMessage: String): String {
        try {
            val apiKey = getGPTApiKey()
                ?: throw Exception("No se pudo obtener la clave API de GPT")
            
            // Preparamos los mensajes para GPT
            val messages = listOf(
                GPTMessage(role = "system", content = SYSTEM_PROMPT),
                GPTMessage(role = "user", content = userMessage)
            )
            
            val request = GPTRequest(
                model = "gpt-4.1-mini",
                messages = messages,
                maxTokens = 500,
                temperature = 0.7
            )
            
            // Hacemos la llamada a la API
            val response = gptApiService.sendMessage(
                authorization = "Bearer $apiKey",
                request = request
            )
            
            if (!response.isSuccessful) {
                Timber.e("GPT API call failed with code: ${response.code()}")
                throw Exception("Error en la respuesta del servidor: ${response.code()}")
            }
            
            val gptResponse = response.body()
            
            // Verificamos si hay errores en la respuesta
            if (gptResponse?.error != null) {
                Timber.e("GPT API returned error: ${gptResponse.error.message}")
                throw Exception("Error de la IA: ${gptResponse.error.message}")
            }
            
            // Extraemos la respuesta
            val aiMessage = gptResponse?.choices?.firstOrNull()?.message?.content
            
            if (aiMessage.isNullOrBlank()) {
                throw Exception("La respuesta de la IA está vacía")
            }
            
            Timber.d("GPT response received successfully")
            return aiMessage.trim()
            
        } catch (e: Exception) {
            Timber.e(e, "Error sending message to GPT")
            
            // Mensajes de error amigables en español
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