package com.sleepmate.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SleepMateApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
            Timber.plant(Timber.DebugTree())

    }
}