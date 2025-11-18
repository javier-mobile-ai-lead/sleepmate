package com.sleepmate.domain.repository

interface GPTRepository {
    /**
     * Envía un mensaje de consulta sobre salud del sueño a GPT
     * @param userMessage mensaje del usuario
     * @return respuesta de la IA
     */
    suspend fun sendSleepHealthQuery(userMessage: String): String
    
    /**
     * Obtiene el API Key de GPT desde Firebase Remote Config
     * @return API Key si está disponible
     */
    suspend fun getGPTApiKey(): String?
}