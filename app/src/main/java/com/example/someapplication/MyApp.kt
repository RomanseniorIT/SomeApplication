package com.example.someapplication

import android.app.Application
import android.content.Context

class MyApp : Application() {
    companion object {
        var context: Context = MyApp()
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}