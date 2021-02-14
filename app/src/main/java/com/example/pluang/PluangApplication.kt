package com.example.pluang

import android.app.Application
import com.example.pluang.di.Koinmodule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PluangApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PluangApplication)
            modules(Koinmodule)
        }

    }
}