package com.example.screenaddiction

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.locks.Lock


class ForegroundService : Service() {
    private val CHANNEL_ID = "ForegroundService Kotlin"

    companion object {
        //Function to start the foregroundService

        fun startService(context: Context, message: String) {
            //Intent is just a description of an operation to be performed.

            val startIntent = Intent(context, ForegroundService::class.java)
            startIntent.putExtra("inputExtra", message)
            //Starts the foreground service

            ContextCompat.startForegroundService(context, startIntent)
        }
        //Function to stop the foregroundService

        fun stopService(context: Context) {
            val stopIntent = Intent(context, ForegroundService::class.java)
            context.stopService(stopIntent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        val bReciever = LockScreenListener()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        this.registerReceiver(bReciever, filter)

        println("ONCREATE")
    }
        //What does intent: Intent? mean?
        override fun onStartCommand(intent: Intent?, flags: Int, StartId: Int): Int{
            println("ONSTART")
            //Do some kind of work on a background thread
            val input = intent?.getStringExtra("inputExtra")
            println(input)
            createNotificationChannel()
            val notificationIntent = Intent(this, MainActivity::class.java)

            val pendingIntent = PendingIntent.getActivity(this, 
                0, notificationIntent, 0
            )
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service Kotlin Example")
                .setContentText(input)
                //.setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent)
                .build()
            startForeground(1, notification)
            //stopSelf();

            //Creates a broadcast receiver and listens for the screen to turn on.

            return START_NOT_STICKY
        }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }



}

