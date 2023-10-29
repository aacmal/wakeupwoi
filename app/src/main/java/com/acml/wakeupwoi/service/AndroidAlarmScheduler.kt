package com.acml.wakeupwoi.service;

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context;
import android.content.Intent
import com.acml.wakeupwoi.ui.screens.alarm.Alarm
import java.time.LocalDateTime
import java.time.ZoneId

class AndroidAlarmScheduler(
    private val context: Context,
) : AlarmScheduler {
    private val alarmManager = context.getSystemService(AlarmManager::class.java)
    override fun schedule(item: Alarm) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra("message", item.label)
        }

        val alarmTime = LocalDateTime.now().atZone(ZoneId.systemDefault()).withHour(item.hour)
            .withMinute(item.minute).withSecond(0).toEpochSecond() * 1000

        println(
            "Alarm scheduled at ${
                LocalDateTime.ofEpochSecond(
                    alarmTime / 1000,
                    0,
                    ZoneId.systemDefault().rules.getOffset(LocalDateTime.now())
                )
            } or $alarmTime"
        )
        println("Alarm scheduled details: ${item.id.toString()} | ${item.hour}:${item.minute} | ${item.label}")

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            PendingIntent.getBroadcast(
                context,
                item.id.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancel(item: Alarm) {
        println("Alarm canceled details: ${item.id.toString()} | ${item.hour}:${item.minute} | ${item.label}")
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.id.hashCode(),
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        ).also { println("Alarm canceled") }
    }
}
