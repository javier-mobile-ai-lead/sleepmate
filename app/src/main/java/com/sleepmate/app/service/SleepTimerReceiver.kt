package com.sleepmate.app.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sleepmate.data.datasource.local.SleepModeDataStore
import com.sleepmate.domain.repository.SleepTimerRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class SleepTimerReceiver : BroadcastReceiver() {
    
    @Inject
    lateinit var sleepTimerRepository: SleepTimerRepository
    
    @Inject 
    lateinit var notificationService: SleepNotificationService
    
    @Inject
    lateinit var doNotDisturbManager: DoNotDisturbManager
    
    // Create a coroutine scope for async operations
    private val receiverScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent?.action != SleepTimerAlarmManager.ACTION_SLEEP_TIMER_FINISHED) {
            return
        }
        
        Timber.d("Sleep timer finished, processing sleep mode activation")
        
        // Use goAsync() to allow asynchronous operations
        val pendingResult = goAsync()
        
        receiverScope.launch {
            try {
                // 1. Save sleep mode state to repository
                val finishTimestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                
                sleepTimerRepository.setSleepModeActivated(
                    isActivated = true,
                    finishTimestamp = finishTimestamp
                )
                
                // 2. Update timer state to inactive
                sleepTimerRepository.setSleepTimerActive(false)
                
                // 3. Send sleep notification
                notificationService.sendSleepNotification()
                
                // 4. Activate Do Not Disturb mode
                doNotDisturbManager.activateDoNotDisturb()
                
                Timber.d("Sleep mode successfully activated at $finishTimestamp")
                
            } catch (e: Exception) {
                Timber.e(e, "Error processing sleep timer completion")
            } finally {
                pendingResult.finish()
            }
        }
    }
}