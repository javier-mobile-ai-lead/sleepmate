package com.sleepmate.app

import android.app.Application
import com.google.firebase.BuildConfig
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltAndroidApp
class SleepMateApplication : Application() {

    companion object {
        const val ONESIGNAL_APP_ID = "58b4d687-fc79-419e-9ce2-8ec209127030"
    }

    override fun onCreate() {
        super.onCreate()

        // Inicializar Timber solo en Debug si lo prefieres, o siempre si usas Crashlytics
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            OneSignal.Debug.logLevel = LogLevel.VERBOSE
        } else {
            OneSignal.Debug.logLevel = LogLevel.NONE
        }

        // Initialize with your OneSignal App ID
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)

        // Use this method to prompt for push notifications.
        // Usamos Dispatchers.Main porque esto invoca UI (el diálogo de permisos)
        CoroutineScope(Dispatchers.Main).launch {
            OneSignal.Notifications.requestPermission(true)
        }
    }
}
