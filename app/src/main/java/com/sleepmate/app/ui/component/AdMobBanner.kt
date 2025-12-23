package com.sleepmate.app.ui.component

import android.app.Activity
import android.util.DisplayMetrics
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdMobBanner(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            AdView(context).apply {
                // 1. Obtenemos las métricas de la pantalla para calcular el ancho
                val displayMetrics = context.resources.displayMetrics
                val screenWidth = (displayMetrics.widthPixels / displayMetrics.density).toInt()

                // 2. Usamos el tamaño adaptativo anclado al ancho actual
                setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, screenWidth))

                // ID de unidad de anuncios de prueba de AdMob
                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}
