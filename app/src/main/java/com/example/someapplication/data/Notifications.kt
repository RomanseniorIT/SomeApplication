package com.example.someapplication.data

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import com.example.someapplication.R
import com.example.someapplication.ui.MainActivity

interface Notifications {
    fun initialize()
    fun showNotification(movieId: Int, title: String)
    fun dismissNotification(movieId: Int)
}

class MovieNotifications(private val context: Context): Notifications {

    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    override fun initialize() {
        if (notificationManagerCompat.getNotificationChannel(CHANNEL_NEW_MOVIE) == null) {
            notificationManagerCompat.createNotificationChannel(
                NotificationChannelCompat.Builder(CHANNEL_NEW_MOVIE,
                    NotificationManagerCompat.IMPORTANCE_HIGH
                )
                    .setName(context.getString(R.string.notif_app_name))
                    .setDescription(context.getString(R.string.notif_new_movie))
                    .build()
            )
        }
    }

    override fun showNotification(movieId: Int, title: String) {
        val contentUri = String.format(DEEP_LINK, movieId).toUri()
        val builder = NotificationCompat.Builder(context, CHANNEL_NEW_MOVIE)
            .setContentTitle(title)
            .setContentText(context.getString(R.string.notif_click_to_check))
            .setSmallIcon(R.drawable.ic_movie_notif)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOnlyAlertOnce(true)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    REQUEST_CONTENT,
                    Intent(context, MainActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(contentUri),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
        notificationManagerCompat.notify(MOVIE_TAG, movieId, builder.build())
    }

    override fun dismissNotification(movieId: Int) {
        notificationManagerCompat.cancel(MOVIE_TAG, movieId)
    }

    companion object {

        private const val CHANNEL_NEW_MOVIE = "new_movie"

        private const val REQUEST_CONTENT = 1

        private const val MOVIE_TAG = "movie"

        private const val DEEP_LINK = "https://com.example.someapplication/movie/%d"
    }
}