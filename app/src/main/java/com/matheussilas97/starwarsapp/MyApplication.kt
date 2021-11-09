package com.matheussilas97.starwarsapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.matheussilas97.starwarsapp.di.listModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = applicationContext

        startKoin {
            androidContext(this@MyApplication)
            modules(listModules)
        }

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: Context? = null
            private set

    }
}