package com.example.prodex.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.res.ResourcesCompat
import com.example.prodex.R
import com.example.prodex.activities.SplashScreen
import com.example.prodex.helpers.Constants
import com.example.prodex.helpers.Constants.CHANNEL_ID
import com.example.prodex.helpers.Permissions
import com.example.prodex.helpers.checkNotificationPermissions
import com.example.prodex.helpers.requestNotificationPermissions
import com.example.prodex.helpers.showSnackBar
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.*

class FirebaseNotificationService : FirebaseMessagingService() {
    val TAG = "Notification TAG"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            val map = remoteMessage.data
            val title = remoteMessage.notification?.title
            val body = remoteMessage.notification?.body
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) createOreoNotification(
                title,
                body,
            ) else createNormalNotification(
                title,
                body,
            )
        } else Log.e("TAG", "onMessageReceived: no data ")
        super.onMessageReceived(remoteMessage)
    }


    override fun onNewToken(s: String) {
        super.onNewToken(s)
    }

    private fun createNormalNotification(
        title: String?, body: String?,
    ) {
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, CHANNEL_ID)
        builder.setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.new_logo)
            .setAutoCancel(true)
            .setColor(ResourcesCompat.getColor(resources, R.color.primary, null))
            .setSound(uri)


        val intent = Intent(this, SplashScreen::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        builder.setContentIntent(pendingIntent)
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(Random().nextInt(85 - 65), builder.build())
    }

    private fun createOreoNotification(
        title: String?, body: String?,
    ) {
        val channel =
            NotificationChannel(
                Constants.CHANNEL_ID,
                "Message",
                NotificationManager.IMPORTANCE_HIGH
            )
        channel.setShowBadge(true)
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
        val intent = Intent(this@FirebaseNotificationService, SplashScreen::class.java)
        val pendingIntent =
            PendingIntent.getActivity(
                this@FirebaseNotificationService,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

        val notification: Notification =
            Notification.Builder(this@FirebaseNotificationService, Constants.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setColor(ResourcesCompat.getColor(resources, R.color.primary, null))
                .setSmallIcon(R.drawable.new_logo)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()

        manager.notify(100, notification)

    }

}