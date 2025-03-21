package com.melancholicbastard.notificationfirebase

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FireBaseService: FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message.notification != null) {
            NotificationUtil.createNotificationChannel(this)
            NotificationUtil.notifyNotification(
                this,
                message.notification!!.title!!,
                message.notification!!.body!!)
        }
    }

}