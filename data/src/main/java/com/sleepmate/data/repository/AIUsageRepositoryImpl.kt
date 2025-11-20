package com.sleepmate.data.repository

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.installations.FirebaseInstallations
import com.sleepmate.domain.repository.AIUsageRepository
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AIUsageRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseInstallations: FirebaseInstallations
) : AIUsageRepository {
    
    companion object {
        private const val COLLECTION_NAME = "ai_chat_usage"
    }
    
    override suspend fun getInstallationId(): String {
        return try {
            firebaseInstallations.id.await()
        } catch (e: Exception) {
            Timber.e(e, "Error getting Firebase Installation ID")
            throw e
        }
    }
    
    override suspend fun getServerTimestamp(): Long {
        return try {
            // Creamos un documento temporal para obtener el timestamp del servidor
            val tempDoc = firestore.collection("temp_timestamp").document()
            val result = tempDoc.set(mapOf("timestamp" to FieldValue.serverTimestamp())).await()
            
            // Leemos el documento para obtener el timestamp
            val snapshot = tempDoc.get().await()
            val serverTimestamp = snapshot.getTimestamp("timestamp")
            
            // Eliminamos el documento temporal
            tempDoc.delete()
            
            serverTimestamp?.toDate()?.time ?: System.currentTimeMillis()
        } catch (e: Exception) {
            Timber.e(e, "Error getting server timestamp, using local time")
            System.currentTimeMillis()
        }
    }
    
    override suspend fun hasUsedAIToday(installationId: String): Boolean {
        return try {
            val document = firestore.collection(COLLECTION_NAME)
                .document(installationId)
                .get()
                .await()
            
            if (!document.exists()) {
                // Si no existe el documento, significa que nunca ha usado la IA
                return false
            }
            
            val lastUsedTimestamp = document.getTimestamp("last_used")
            if (lastUsedTimestamp == null) {
                return false
            }
            
            // Obtenemos la hora actual del servidor
            val currentServerTime = getServerTimestamp()
            
            // Convertimos ambas fechas a UTC y comparamos solo el día
            val lastUsedCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                timeInMillis = lastUsedTimestamp.toDate().time
            }
            
            val currentCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                timeInMillis = currentServerTime
            }
            
            // Verificamos si ambas fechas corresponden al mismo día
            val isSameDay = lastUsedCalendar.get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR) &&
                    lastUsedCalendar.get(Calendar.DAY_OF_YEAR) == currentCalendar.get(Calendar.DAY_OF_YEAR)
            
            Timber.d("Last used: ${lastUsedTimestamp.toDate()}, Current: ${Date(currentServerTime)}, Same day: $isSameDay")
            
            return isSameDay
            
        } catch (e: Exception) {
            Timber.e(e, "Error checking if AI was used today")
            // En caso de error, permitimos el uso para no bloquear al usuario
            return false
        }
    }
    
    override suspend fun recordAIUsage(installationId: String) {
        try {
            val document = firestore.collection(COLLECTION_NAME)
                .document(installationId)
            
            val data = mapOf(
                "last_used" to FieldValue.serverTimestamp(),
                "installation_id" to installationId
            )
            
            document.set(data).await()
            Timber.d("AI usage recorded successfully for installation: $installationId")
            
        } catch (e: Exception) {
            Timber.e(e, "Error recording AI usage")
            throw e
        }
    }
}