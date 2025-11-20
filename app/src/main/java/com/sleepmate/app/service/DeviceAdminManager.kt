package com.sleepmate.app.service

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceAdminManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val devicePolicyManager =
        context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager

    val adminComponent: ComponentName by lazy {
        ComponentName(context, DeviceAdminReceiver::class.java)
    }

    fun isDeviceAdminActive(): Boolean {
        return devicePolicyManager.isAdminActive(adminComponent)
    }

    fun lockScreen() {
        if (isDeviceAdminActive()) {
            try {
                Timber.d("Attempting to lock screen.")
                devicePolicyManager.lockNow()
            } catch (e: Exception) {
                Timber.e(e, "Failed to lock screen.")
            }
        } else {
            Timber.w("Cannot lock screen: Device admin not active.")
        }
    }
}
