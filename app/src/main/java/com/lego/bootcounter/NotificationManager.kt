package com.lego.bootcounter

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.lego.bootcounter.NotificationWorker.Companion.MESSAGE_DATA
import com.lego.bootcounter.domain.models.CounterModel
import com.lego.bootcounter.presentation.showMessage
import java.util.concurrent.TimeUnit

class BootNotificationManager {

    companion object {
        const val WORKER_NAME = "BootNotificationWork"
    }

    fun schedulePeriodicWork(context: Context, currentCounter: CounterModel) {

        val message = showMessage(context, currentCounter)

        val data = Data.Builder()
            .putString(MESSAGE_DATA, message)
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val notificationWorkRequest =
                PeriodicWorkRequest.Builder(NotificationWorker::class.java, 15, TimeUnit.MINUTES)
                    .setConstraints(
                        Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                            .build()
                    )
                    .setInputData(data)
                    .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORKER_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                notificationWorkRequest
            )
        }
    }

}