package com.lego.bootcounter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.lego.bootcounter.domain.models.CounterModel
import com.lego.bootcounter.domain.usecase.GetCounterUseCase
import com.lego.bootcounter.domain.usecase.SaveCounterUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BootCompletedReceiver : BroadcastReceiver(), KoinComponent {

    private val saveCounterUseCase by inject<SaveCounterUseCase>()
    private val gatCounterUseCase by inject<GetCounterUseCase>()

    private val notificationManager = BootNotificationManager()

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            CoroutineScope(Dispatchers.IO).launch {
                val currentCounter = gatCounterUseCase.getCounter()
                val newCounter = CounterModel(
                    currentCounter.count + 1,
                    System.currentTimeMillis()
                )
                saveCounterUseCase.save(newCounter)
                notificationManager.schedulePeriodicWork(context, newCounter)
            }
        }
    }
}