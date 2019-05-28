package com.rubin.jetpackcomponents.workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rubin.jetpackcomponents.R

class SendWorker(context: Context, parameters: WorkerParameters) : Worker(context, parameters) {

    override fun doWork(): Result {

        val taskData = inputData
        val taskDataString = taskData.getString(WorkManagerMainActivity.MESSAGE_STATUS)

        showNotification("Work Manager", taskDataString ?: "Message Sent")

        val outputData = Data.Builder().putString(WORK_RESULT, "Task Finished").build()

        return Result.success(outputData)
    }

    private fun showNotification(task: String, desc: String) {
        val manager = applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "message_channel"
        val channelName = "message_name"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle(task)
            .setContentText(desc)
            .setSmallIcon(R.mipmap.ic_launcher)

        manager.notify(1, builder.build())
    }

    companion object {
        const val WORK_RESULT = "work_result"
    }
}