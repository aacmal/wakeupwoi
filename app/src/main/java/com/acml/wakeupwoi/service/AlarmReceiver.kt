package com.acml.wakeupwoi.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat


class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("message")
        println("AlarmReceiver: $message")

        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        // Create a notification channel
        val channelId = "default"
        val channelName = "Default Channel"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, channelName, importance)
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        // Create a notification builder
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Alarm Notification")
            .setContentText("Alarm is ringing!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(notification)

        // Show the notification
        notificationManager.notify(0, builder.build())
    }
}