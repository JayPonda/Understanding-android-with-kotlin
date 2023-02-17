package com.demo.helloWorld.handlers.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.core.app.NotificationCompat
import com.demo.helloWorld.data.Card
import com.demo.helloWorld.fragments.leaves.LocalNotificationFragment.Companion.CHANNEL_ID
import com.demo.helloWorld.records.StaticRecords.multiplyCardList
import java.util.*


class NotificationHandler(private val DELAY: Long = 5L, private val icon: Int = android.R.drawable.ic_notification_overlay) {
    private var  iceCreamList: Array<Card> = multiplyCardList(1)

    fun generateNotificationBuilderObj(
        str: Array<String?>,
        context: Context,
        isTimeOut: Boolean,
        isBig: Boolean,
        isLargeImage: Boolean,
        intent: Intent?,
    ): NotificationCompat.Builder {
        val newBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(str[0])
            .setContentText(str[1])
            .setSmallIcon(icon)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOnlyAlertOnce(true)
            .setColor(Color.parseColor("#ff00aac2"))
        if (isBig) {
            newBuilder.setStyle(NotificationCompat.BigTextStyle()
                .bigText(str[2]))
        }
        if (intent != null) newBuilder.setAutoCancel(true)
            .setContentIntent(PendingIntent.getActivity(context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE))
        if (isTimeOut) newBuilder.setTimeoutAfter(DELAY)
        if (isLargeImage) {
            val randomVal: Int = Random().nextInt( iceCreamList.size)
            newBuilder.setLargeIcon(BitmapFactory.decodeResource(context.resources,
                  iceCreamList[randomVal].imgRec))
        }
        return newBuilder
    }

    fun generateNotificationBuilderObjExpandable(
        str: Array<String?>,
        context: Context,
        isTimeOut: Boolean,
        isLargeImage: Boolean,
        intent: Intent?,
    ): NotificationCompat.Builder {
        val newBuilder: NotificationCompat.Builder = generateNotificationBuilderObj(str, context, isTimeOut, false, isLargeImage, intent)
        val randomVal: Int = Random().nextInt(iceCreamList.size)
        val largeIcon =
            BitmapFactory.decodeResource(context.resources,   iceCreamList[randomVal].imgRec)
        val imageBuild = NotificationCompat.BigPictureStyle()
        imageBuild.bigPicture(largeIcon)
        imageBuild.bigLargeIcon(largeIcon).build()
        newBuilder.setStyle(imageBuild)
        return newBuilder
    }
}