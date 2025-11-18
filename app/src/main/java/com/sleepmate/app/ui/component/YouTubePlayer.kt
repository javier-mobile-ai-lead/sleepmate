package com.sleepmate.app.ui.component

import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.sleepmate.app.R
import timber.log.Timber

@Composable
fun YouTubePlayer(
    videoId: String,
    modifier: Modifier = Modifier,
    onError: ((String) -> Unit)? = null,
    enableFullscreen: Boolean = true,
    onEnterFullscreen: ((View) -> Unit)? = null,
    onExitFullscreen: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var isPlayerReady by remember { mutableStateOf(false) }
    var hasError by remember { mutableStateOf(false) }
    val surfaceColor = MaterialTheme.colorScheme.surface.toArgb()
    var youTubePlayerView = YouTubePlayerView(context)
    
    if (hasError) {
        ErrorPlayerContent(
            onRetry = { hasError = false },
            modifier = modifier
        )
        return
    }
    
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        DisposableEffect(lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                // The YouTube player handles lifecycle automatically
                when (event) {
                    Lifecycle.Event.ON_PAUSE -> {
                        Timber.d("YouTube player paused due to lifecycle")
                    }
                    Lifecycle.Event.ON_RESUME -> {
                        Timber.d("YouTube player resumed due to lifecycle")
                    }
                    else -> {}
                }
            }

            lifecycleOwner.lifecycle.addObserver(observer)

            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
        AndroidView(
            factory = { ctx ->
                 YouTubePlayerView(ctx).apply {
                    enableAutomaticInitialization = false
                    isPlayerReady = false
                    // Configure IFrame options
                    val options = IFramePlayerOptions.Builder()
                        .controls(1) // Show controls
                        .fullscreen(if (enableFullscreen) 1 else 0)
                        .rel(0) // Don't show related videos
                        .modestBranding(1) // Modest branding
                        .build()

                    // Set background color to match theme
                    setBackgroundColor(surfaceColor)

                    initialize(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            super.onReady(youTubePlayer)

                            try {
                                youTubePlayer.cueVideo(videoId, 0f)
                                isPlayerReady = true
                                Timber.d("YouTube player ready for video: $videoId")
                            } catch (e: Exception) {
                                Timber.e(e, "Error loading video: $videoId")
                                hasError = true
                                onError?.invoke("Este video no está disponible.")
                            }
                        }

                        override fun onError(youTubePlayer: YouTubePlayer, error: com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants.PlayerError) {
                            super.onError(youTubePlayer, error)
                            Timber.e("YouTube player error: $error for video: $videoId")
                            hasError = true
                            onError?.invoke("Este video no está disponible.")
                        }
                    }, options)

                    // Handle fullscreen if enabled
                    if (enableFullscreen) {
                        addFullscreenListener(object : FullscreenListener {
                            override fun onEnterFullscreen(fullscreenView: android.view.View, exitFullscreen: () -> Unit) {
                                Timber.d("Entered fullscreen mode")
                                onEnterFullscreen?.invoke(fullscreenView)
                            }

                            override fun onExitFullscreen() {
                                Timber.d("Exited fullscreen mode")
                                onExitFullscreen?.invoke()
                            }
                        })
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        
        // Loading indicator
        if (!isPlayerReady && !hasError) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
    
    // Handle lifecycle events

}

@Composable
private fun ErrorPlayerContent(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.video_not_available),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    textAlign = TextAlign.Center
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                TextButton(
                    onClick = onRetry,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Text(stringResource(R.string.retry))
                }
            }
        }
    }
}