package com.example.teamanagement.worker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.teamanagement.R
import com.example.teamanagement.adapter.Constant.CHANNEL_ID
import com.example.teamanagement.adapter.Constant.NOTIFICATION_ID
import com.example.teamanagement.viewModel.WorkerViewModel


class TeaWorker(
    private val ctx: Context, params: WorkerParameters
) : Worker(ctx, params) {
    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }


    private fun showNotification() {
        val builder = NotificationCompat.Builder(ctx, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_coffee_24)
            .setContentTitle("Notice")
            .setContentTitle("Time to drink tea! Have a good day")
            .setContentIntent(createPendingIntent())
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        with(NotificationManagerCompat.from(ctx)) {
            notify(NOTIFICATION_ID, builder)
        }

    }

    private fun createPendingIntent(): PendingIntent {
        val intent = Intent(ctx, WorkerViewModel::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE
        } else {
            PendingIntent.FLAG_ONE_SHOT
        }

        return PendingIntent.getActivity(
            ctx,
            0,
            intent,
            flag
        )
    }

}