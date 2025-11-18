package com.sleepmate.app.ui.screen.aihelp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sleepmate.data.datasource.local.ChatHistoryDataStore
import com.sleepmate.domain.model.ChatMessage
import com.sleepmate.domain.repository.AIUsageRepository
import com.sleepmate.domain.repository.GPTRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

data class AIHelpUiState(
    val messages: List<ChatMessage> = emptyList(),
    val isLoading: Boolean = false,
    val canSendMessage: Boolean = true,
    val currentMessage: String = "",
    val error: String? = null,
    val usageBlocked: Boolean = false,
    val blockMessage: String? = null
)

@HiltViewModel
class AIHelpViewModel @Inject constructor(
    private val aiUsageRepository: AIUsageRepository,
    private val gptRepository: GPTRepository,
    private val chatHistoryDataStore: ChatHistoryDataStore
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(AIHelpUiState())
    val uiState: StateFlow<AIHelpUiState> = _uiState.asStateFlow()
    
    init {
        loadChatHistory()
        checkDailyUsage()
    }
    
    private fun loadChatHistory() {
        viewModelScope.launch {
            try {
                chatHistoryDataStore.getChatHistory()
                    .catch { exception ->
                        Timber.e(exception, "Error loading chat history")
                    }
                    .collect { messages ->
                        _uiState.value = _uiState.value.copy(
                            messages = messages.sortedBy { it.timestamp }
                        )
                    }
            } catch (e: Exception) {
                Timber.e(e, "Error observing chat history")
            }
        }
    }
    
    private fun checkDailyUsage() {
        viewModelScope.launch {
            try {
                val installationId = aiUsageRepository.getInstallationId()
                val hasUsedToday = aiUsageRepository.hasUsedAIToday(installationId)
                
                if (hasUsedToday) {
                    _uiState.value = _uiState.value.copy(
                        usageBlocked = true,
                        canSendMessage = false,
                        blockMessage = "Ya realizaste tu consulta diaria. Vuelve mañana para hacer una nueva pregunta."
                    )
                    Timber.d("Daily usage blocked for installation: $installationId")
                } else {
                    _uiState.value = _uiState.value.copy(
                        usageBlocked = false,
                        canSendMessage = true,
                        blockMessage = null
                    )
                    Timber.d("Daily usage available for installation: $installationId")
                }
                
            } catch (e: Exception) {
                Timber.e(e, "Error checking daily usage")
                // En caso de error, permitimos el uso para no bloquear al usuario
                _uiState.value = _uiState.value.copy(
                    usageBlocked = false,
                    canSendMessage = true,
                    error = "Error al verificar el uso diario"
                )
            }
        }
    }
    
    fun updateCurrentMessage(message: String) {
        _uiState.value = _uiState.value.copy(currentMessage = message)
    }
    
    fun sendMessage() {
        val currentMessage = _uiState.value.currentMessage.trim()
        
        if (currentMessage.isEmpty()) {
            _uiState.value = _uiState.value.copy(
                error = "Por favor escribe tu consulta"
            )
            return
        }
        
        if (_uiState.value.usageBlocked) {
            _uiState.value = _uiState.value.copy(
                error = "Ya realizaste tu consulta diaria"
            )
            return
        }
        
        viewModelScope.launch {
            try {
                // Deshabilitamos el envío y mostramos loading
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    canSendMessage = false,
                    error = null,
                    currentMessage = ""
                )
                
                // Agregamos el mensaje del usuario al historial
                val userMessage = ChatMessage(
                    id = UUID.randomUUID().toString(),
                    content = currentMessage,
                    isFromUser = true,
                    timestamp = System.currentTimeMillis()
                )
                
                chatHistoryDataStore.addMessage(userMessage)
                
                // Agregamos un mensaje de loading de la IA
                val loadingMessageId = UUID.randomUUID().toString()
                val loadingMessage = ChatMessage(
                    id = loadingMessageId,
                    content = "SleepMate está pensando...",
                    isFromUser = false,
                    timestamp = System.currentTimeMillis(),
                    isLoading = true
                )
                
                chatHistoryDataStore.addMessage(loadingMessage)
                
                // Obtenemos la respuesta de GPT
                val aiResponse = gptRepository.sendSleepHealthQuery(currentMessage)
                
                // Reemplazamos el mensaje de loading con la respuesta real
                val aiMessage = ChatMessage(
                    id = loadingMessageId,
                    content = aiResponse,
                    isFromUser = false,
                    timestamp = System.currentTimeMillis(),
                    isLoading = false
                )
                
                chatHistoryDataStore.updateMessage(loadingMessageId, aiMessage)
                
                // Registramos el uso diario
                val installationId = aiUsageRepository.getInstallationId()
                aiUsageRepository.recordAIUsage(installationId)
                
                // Bloqueamos futuras consultas
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    usageBlocked = true,
                    canSendMessage = false,
                    blockMessage = "Consulta completada. Vuelve mañana para hacer una nueva pregunta."
                )
                
                Timber.d("Message sent and usage recorded successfully")
                
            } catch (e: Exception) {
                Timber.e(e, "Error sending message")
                
                // Removemos el mensaje de loading si hubo error
                val currentMessages = _uiState.value.messages
                val loadingMessage = currentMessages.find { it.isLoading }
                
                if (loadingMessage != null) {
                    val errorMessage = ChatMessage(
                        id = loadingMessage.id,
                        content = "Lo siento, no pude procesar tu consulta. ${e.message ?: "Intenta nuevamente más tarde."}",
                        isFromUser = false,
                        timestamp = System.currentTimeMillis(),
                        isLoading = false
                    )
                    
                    chatHistoryDataStore.updateMessage(loadingMessage.id, errorMessage)
                }
                
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    canSendMessage = !_uiState.value.usageBlocked,
                    error = e.message ?: "Error al enviar el mensaje"
                )
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
    
    fun clearChatHistory() {
        viewModelScope.launch {
            try {
                chatHistoryDataStore.clearHistory()
                Timber.d("Chat history cleared by user")
            } catch (e: Exception) {
                Timber.e(e, "Error clearing chat history")
                _uiState.value = _uiState.value.copy(
                    error = "Error al borrar el historial"
                )
            }
        }
    }
    
    fun retryCheckUsage() {
        checkDailyUsage()
    }
}