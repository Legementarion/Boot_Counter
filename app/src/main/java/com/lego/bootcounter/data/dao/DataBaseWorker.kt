package com.lego.bootcounter.data.dao

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.lego.bootcounter.data.models.Counter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val database = CounterDataBase.getInstance(applicationContext)
            database.counterDao().save(Counter(0, System.currentTimeMillis()))
            Result.success()
        } catch (ex: Exception) {
            Result.failure()
        }
    }
}