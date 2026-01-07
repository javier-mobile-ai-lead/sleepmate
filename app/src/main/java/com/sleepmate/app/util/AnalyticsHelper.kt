package com.sleepmate.app.util

import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics // Importante para usar las constantes estándar
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent

object AnalyticsHelper {

    /**
     * ✅ ÚNICA FUNCIÓN PARA TODOS LOS BOTONES
     * Usa esto para cualquier clic importante en la app.
     *
     * @param elementId  El nombre único del botón (ej: "btn_save", "card_sleep_timer")
     * @param screenName La pantalla donde ocurrió (ej: "Home", "Settings")
     */
    fun logClick(elementId: String, screenName: String) {
        // Usamos un solo nombre de evento: "select_content" (es un estándar de Google)
        // O podrías usar uno propio como "ui_click"
        Firebase.analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {

            // 1. ¿Qué tocó? (ID del elemento)
            param(FirebaseAnalytics.Param.ITEM_ID, elementId)

            // 2. ¿Qué tipo de cosa es? (Botón, Card, Imagen)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "button")

            // 3. ¿Dónde estaba? (Contexto)
            param("screen_name", screenName)
        }
    }

    // ... puedes mantener las otras funciones si son para cosas muy distintas (como errores o compras)
}
