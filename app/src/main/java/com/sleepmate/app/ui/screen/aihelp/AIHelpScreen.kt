package com.sleepmate.app.ui.screen.aihelp

import android.app.Activity
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.sleepmate.app.R
import com.sleepmate.app.util.AnalyticsHelper
import com.sleepmate.domain.model.ChatMessage
import kotlinx.coroutines.delay
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AIHelpScreen(
    onNavigateBack: () -> Unit,
    viewModel: AIHelpViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val listState = rememberLazyListState()
    var showClearDialog by remember { mutableStateOf(false) }
    
    // AdMob Rewarded Ad
    val context = LocalContext.current
    var rewardedAd: RewardedAd? by remember { mutableStateOf(null) }
    var isAdLoading by remember { mutableStateOf(false) }

    fun loadRewardedAd() {
        if (isAdLoading) return
        isAdLoading = true
        
        // ID de prueba de Google para Rewarded Ads
        val adUnitId = "ca-app-pub-3940256099942544/5224354917" 
        val adRequest = AdRequest.Builder().build()
        
        RewardedAd.load(context, adUnitId, adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Timber.e("AdMob: Error al cargar rewarded ad: ${adError.message}")
                rewardedAd = null
                isAdLoading = false
            }

            override fun onAdLoaded(ad: RewardedAd) {
                Timber.d("AdMob: Rewarded ad cargado correctamente")
                rewardedAd = ad
                isAdLoading = false
                
                // Configurar callbacks
                ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdClicked() {
                        Timber.d("AdMob: Ad clicked")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        Timber.d("AdMob: Ad dismissed")
                        rewardedAd = null
                        loadRewardedAd() // Pre-cargar el siguiente anuncio
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                        Timber.e("AdMob: Error al mostrar ad: ${adError.message}")
                        rewardedAd = null
                    }

                    override fun onAdImpression() {
                        Timber.d("AdMob: Ad impression")
                    }

                    override fun onAdShowedFullScreenContent() {
                        Timber.d("AdMob: Ad showed")
                    }
                }
            }
        })
    }
    
    // Cargar anuncio al iniciar la pantalla si el usuario está bloqueado o cerca del límite
    LaunchedEffect(Unit) {
        loadRewardedAd()
    }
    
    fun showRewardedAd() {
        val activity = context as? Activity
        if (activity != null && rewardedAd != null) {
            rewardedAd?.show(activity) { rewardItem ->
                // El usuario vio el anuncio completo
                Timber.d("AdMob: Usuario ganó recompensa: ${rewardItem.amount} ${rewardItem.type}")
                viewModel.unlockBonusQueries()
            }
        } else {
            Timber.e("AdMob: El anuncio no estaba listo o el contexto no es una Activity")
            if (rewardedAd == null) {
                loadRewardedAd() // Intentar cargar de nuevo
            }
        }
    }
    
    // Auto-scroll a los mensajes más recientes
    LaunchedEffect(uiState.messages.size) {
        if (uiState.messages.isNotEmpty()) {
            delay(100)
            listState.animateScrollToItem(uiState.messages.size - 1)
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = stringResource(R.string.ai_help_title),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.ai_help_subtitle),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 12.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                actions = {
                    if (uiState.messages.isNotEmpty()) {
                        IconButton(onClick = {
                            AnalyticsHelper.logClick("clear_chat_button", "AIHelpScreen")
                            showClearDialog = true }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = stringResource(R.string.clear_chat),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            )
        },
        snackbarHost = {
            // Manejo de errores con Snackbar
            uiState.error?.let { error ->
                LaunchedEffect(error) {
                    delay(3000)
                    viewModel.clearError()
                }
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = error,
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f)
                            )
                            
                            TextButton(
                                onClick = viewModel::clearError,
                                colors = ButtonDefaults.textButtonColors(
                                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                                )
                            ) {
                                Text(stringResource(R.string.close))
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Lista de mensajes
            Box(modifier = Modifier.weight(1f)) {
                if (uiState.messages.isEmpty()) {
                    EmptyStateContent(
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item { Spacer(modifier = Modifier.height(8.dp)) }
                        
                        items(uiState.messages, key = { it.id }) { message ->
                            Column(Modifier.fillMaxSize()) {
                                AnimatedVisibility(
                                    visible = true,
                                    enter = slideInVertically() + fadeIn(),
                                    exit = slideOutVertically() + fadeOut()
                                ) {
                                    ChatBubble(message = message)
                                }
                            }
                        }
                        
                        item { Spacer(modifier = Modifier.height(16.dp)) }
                    }
                }
                
                // Mensaje de uso bloqueado
                if (uiState.usageBlocked && uiState.blockMessage != null) {
                    Card(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(16.dp)
                            .padding(top = 56.dp), // Ajuste para que no tape el TopBar si está expanded
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = uiState.blockMessage.orEmpty(),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center
                            )
                            
                            Spacer(modifier = Modifier.height(12.dp))
                            
                            Button(
                                onClick = {
                                    AnalyticsHelper.logClick("rewarded_ad_button", "AIHelpScreen")
                                    showRewardedAd() },
                                enabled = rewardedAd != null,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                if (rewardedAd == null) {
                                    if (isAdLoading) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(16.dp),
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            strokeWidth = 2.dp
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Cargando anuncio...")
                                    } else {
                                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text("Reintentar cargar anuncio")
                                    }
                                } else {
                                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Ver video para desbloquear")
                                }
                            }
                            
                            if (rewardedAd == null && !isAdLoading) {
                                TextButton(onClick = { loadRewardedAd() }) {
                                    Text("Reintentar cargar anuncio", fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }
            }
            
            // Input section
            ChatInputSection(
                currentMessage = uiState.currentMessage,
                onMessageChange = viewModel::updateCurrentMessage,
                onSendMessage = {
                    viewModel.sendMessage()
                    keyboardController?.hide()
                },
                canSendMessage = uiState.canSendMessage && !uiState.isLoading,
                isLoading = uiState.isLoading
            )
        }
    }
    
    // Clear confirmation dialog
    if (showClearDialog) {
        AlertDialog(
            onDismissRequest = { showClearDialog = false },
            title = { Text(stringResource(R.string.clear_confirmation_title)) },
            text = { Text(stringResource(R.string.clear_confirmation_message)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.clearChatHistory()
                        showClearDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text(stringResource(R.string.clear))
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearDialog = false }) {
                    Text(stringResource(R.string.close))
                }
            }
        )
    }
}

@Composable
private fun ChatBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier
) {
    val isUser = message.isFromUser
    
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Card(
            modifier = Modifier.widthIn(max = 280.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isUser) 
                    MaterialTheme.colorScheme.primary 
                else 
                    MaterialTheme.colorScheme.surfaceVariant
            ),
            shape = RoundedCornerShape(
                topStart = if (isUser) 16.dp else 4.dp,
                topEnd = if (isUser) 4.dp else 16.dp,
                bottomStart = 16.dp,
                bottomEnd = 16.dp
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                // Etiqueta de usuario/IA
                Text(
                    text = if (isUser) 
                        stringResource(R.string.you) 
                    else 
                        stringResource(R.string.sleepmate_ai),
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isUser) 
                        MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                    else 
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                
                // Contenido del mensaje
                if (message.isLoading) {
                    TypingAnimation()
                } else {
                    Text(
                        text = message.content,
                        color = if (isUser) 
                            MaterialTheme.colorScheme.onPrimary 
                        else 
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun TypingAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "typing")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        ),
        label = "typing_alpha"
    )
    
    Text(
        text = stringResource(R.string.ai_thinking),
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha),
        style = MaterialTheme.typography.bodyMedium.copy(fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
    )
}

@Composable
private fun ChatInputSection(
    currentMessage: String,
    onMessageChange: (String) -> Unit,
    onSendMessage: () -> Unit,
    canSendMessage: Boolean,
    isLoading: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedTextField(
                value = currentMessage,
                onValueChange = onMessageChange,
                modifier = Modifier.weight(1f),
                placeholder = { 
                    Text(
                        stringResource(R.string.ai_chat_hint),
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                    )
                },
                maxLines = 4,
                shape = RoundedCornerShape(24.dp),
                enabled = canSendMessage
            )
            
            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(56.dp) // tamaño similar al FAB
                    .clip(CircleShape)
                    .background(
                        if (canSendMessage && currentMessage.isNotBlank())
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    )
                    .clickable(enabled = !isLoading && canSendMessage && currentMessage.isNotBlank()) {
                        AnalyticsHelper.logClick("send_message_button", "AIHelpScreen")
                        onSendMessage()
                    },
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Icon(
                        Icons.Default.Send,
                        contentDescription = stringResource(R.string.send_message),
                        tint = if (canSendMessage && currentMessage.isNotBlank())
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyStateContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "🤖",
                style = MaterialTheme.typography.displayMedium
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = stringResource(R.string.no_messages_yet),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = stringResource(R.string.no_messages_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }
    }
}