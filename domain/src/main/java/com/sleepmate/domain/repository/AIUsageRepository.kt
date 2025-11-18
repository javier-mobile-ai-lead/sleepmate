package com.sleepmate.domain.repository

interface AIUsageRepository {
    /**
     * Obtiene el Firebase Installation ID único del dispositivo
     */
    suspend fun getInstallationId(): String
    
    /**
     * Obtiene la hora actual del servidor de Firebase en UTC (timestamp)
     */
    suspend fun getServerTimestamp(): Long
    
    /**
     * Verifica si el usuario ya utilizó la IA hoy
     * @param installationId ID único del dispositivo
     * @return true si ya utilizó la IA hoy, false en caso contrario
     */
    suspend fun hasUsedAIToday(installationId: String): Boolean
    
    /**
     * Registra el uso de la IA en Firestore
     * @param installationId ID único del dispositivo
     */
    suspend fun recordAIUsage(installationId: String)
}