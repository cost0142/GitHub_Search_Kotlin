package com.androidfinal_hygor_costa

// Created by Hygor Costa November 1 2022

import android.content.Context
import android.app.Application
import android.annotation.SuppressLint

class TheApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }
}

