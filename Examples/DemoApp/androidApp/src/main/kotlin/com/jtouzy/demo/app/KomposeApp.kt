package com.jtouzy.demo.app

import android.app.Application
import com.jtouzy.demo.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KomposeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KomposeApp)
            modules(appModule)
        }
    }
}
