package com.example.chatapp

import android.app.Application
import com.example.chatapp.helper.appModule
import com.example.chatapp.helper.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ChatAppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ChatAppApplication)
            modules(appModule, viewModelModule)
        }
    }
}