package com.sleepmate.app.ui.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sleepmate.app.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(onNavigateToHome: () -> Unit) {
    val scale = remember { Animatable(0.6f) }
    val animAlpha = remember { Animatable(0f) }
    val offsetY = remember { Animatable(40f) }

    LaunchedEffect(Unit) {
        launch {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = { OvershootInterpolator(2f).getInterpolation(it) }
                )
            )
        }
        launch {
            animAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(1000)
            )
        }
        launch {
            offsetY.animateTo(
                targetValue = 0f,
                animationSpec = tween(1000, easing = LinearOutSlowInEasing)
            )
        }

        delay(2200L)
        onNavigateToHome()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo), // Asegúrate de tenerlo en res/drawable
                contentDescription = "SleepMate Logo",
                modifier = Modifier
                    .size(160.dp)
                    .graphicsLayer {
                        scaleX = scale.value
                        scaleY = scale.value
                        alpha = animAlpha.value
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "SleepMate",
                color = Color.White,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .offset(y = offsetY.value.dp)
                    .alpha(animAlpha.value)
            )
        }
    }
}
