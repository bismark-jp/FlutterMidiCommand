package com.invisiblewrench.fluttermidicommand_example

import io.flutter.embedding.android.FlutterActivity
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder


class MainActivity: FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(Intent(this, DetectTerminationService::class.java))
    }

}

class DetectTerminationService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        // Force terminate VirtualDeviceService
        val context = this.applicationContext
        val comp = ComponentName(context, "com.invisiblewrench.fluttermidicommand.VirtualDeviceService")
        val pm = context.packageManager
        pm.setComponentEnabledSetting(comp, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)

        stopSelf()
    }
}