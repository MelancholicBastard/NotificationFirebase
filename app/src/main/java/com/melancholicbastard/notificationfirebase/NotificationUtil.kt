package com.melancholicbastard.notificationfirebase

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

object NotificationUtil {
    fun createNotificationChannel(context: Context) {
        val channel = NotificationChannel(
            "MainActivity",
            "MainActivity",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

    fun notifyNotification(context: Context, title: String, text: String) {
        val intent = Intent(context, MainActivity::class.java)
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, "MainActivity")
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.mipmap.stenly)
            .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
            .setContentIntent(pendingIntent)
//            .addAction(R.mipmap.music_back, "Previous", getPandingIntent(context, 2))
//            .addAction(R.drawable.pause, "Stop/play", getPandingIntent(context, 3))
//            .addAction(R.mipmap.music_forward, "Next", getPandingIntent(context, 4))
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {

            }
            notify(1, notification.build())
        }
    }

//    private fun getPandingIntent(context: Context, requestCode: Int): PendingIntent? {
//        val intent = Intent(context, NotificationResivedInformation::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//            action = "Button${requestCode}"
//        }
//        return PendingIntent.getBroadcast(
//            context,
//            requestCode,
//            intent,
//            PendingIntent.FLAG_IMMUTABLE
//        )
//    }
}