package com.lego.bootcounter

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class BootCounterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@BootCounterApplication)
            // declare modules
            modules(appModule, presentation, domain, data)
        }

    }

}