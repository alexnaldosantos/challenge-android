package com.amedigital.challenge_home

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@KoinApp)
        }
    }
}