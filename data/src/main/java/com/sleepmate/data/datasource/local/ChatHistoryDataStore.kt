package com.sleepmate.data.datasource.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sleepmate.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

private val Context.chatHistoryDataStore: DataStore<Preferences> by preferencesDataStore(name = "chat_history_preferences")

@Singleton
class ChatHistoryDataStore @Inject constructor(
    private val context: Context,
    private val gson: Gson
) {
    companion object {
        private val CHAT_HISTORY_KEY = stringPreferencesKey("chat_history")
        private const val MAX_MESSAGES = 50 // Limitamos a 50 mensajes para no saturar el storage
    }
    
    fun getChatHistory(): Flow<List<ChatMessage>> {
        return context.chatHistoryDataStore.data.map { preferences ->
            val historyJson = preferences[CHAT_HISTORY_KEY] ?: "[]"
            try {
                val type = object : TypeToken<List<ChatMessage>>() {}.type
                gson.fromJson(historyJson, type) ?: emptyList()
            } catch (e: Exception) {
                Timber.e(e, "Error deserializing chat history")
                emptyList()
            }
        }
    }
    
    suspend fun addMessage(message: ChatMessage) {
        try {
            context.chatHistoryDataStore.edit { preferences ->
                val currentHistoryJson = preferences[CHAT_HISTORY_KEY] ?: "[]"
                val type = object : TypeToken<List<ChatMessage>>() {}.type
                val currentHistory = try {
                    gson.fromJson<List<ChatMessage>>(currentHistoryJson, type) ?: emptyList()
                } catch (e: Exception) {
                    Timber.e(e, "Error deserializing current chat history")
                    emptyList()
                }
                
                // Agregamos el nuevo mensaje
                val updatedHistory = currentHistory.toMutableList().apply { 
                    add(message) 
                }
                
                // Limitamos el número de mensajes almacenados
                val trimmedHistory = if (updatedHistory.size > MAX_MESSAGES) {
                    updatedHistory.takeLast(MAX_MESSAGES)
                } else {
                    updatedHistory
                }
                
                preferences[CHAT_HISTORY_KEY] = gson.toJson(trimmedHistory)
            }
        } catch (e: Exception) {
            Timber.e(e, "Error adding message to chat history")
        }
    }
    
    suspend fun updateMessage(messageId: String, updatedMessage: ChatMessage) {
        try {
            context.chatHistoryDataStore.edit { preferences ->
                val currentHistoryJson = preferences[CHAT_HISTORY_KEY] ?: "[]"
                val type = object : TypeToken<List<ChatMessage>>() {}.type
                val currentHistory = try {
                    gson.fromJson<List<ChatMessage>>(currentHistoryJson, type) ?: emptyList()
                } catch (e: Exception) {
                    Timber.e(e, "Error deserializing current chat history")
                    emptyList()
                }
                
                val updatedHistory = currentHistory.map { message ->
                    if (message.id == messageId) updatedMessage else message
                }
                
                preferences[CHAT_HISTORY_KEY] = gson.toJson(updatedHistory)
            }
        } catch (e: Exception) {
            Timber.e(e, "Error updating message in chat history")
        }
    }
    
    suspend fun clearHistory() {
        try {
            context.chatHistoryDataStore.edit { preferences ->
                preferences[CHAT_HISTORY_KEY] = "[]"
            }
            Timber.d("Chat history cleared successfully")
        } catch (e: Exception) {
            Timber.e(e, "Error clearing chat history")
        }
    }
}