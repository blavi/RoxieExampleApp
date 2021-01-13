package com.example.roxieexampleapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RoxieExampleApp : Application()  {
    override fun onCreate() {
        super.onCreate()
    }
}