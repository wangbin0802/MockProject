package com.stareme.ocbcsimple

import android.app.Application
import com.stareme.ocbcsimple.http.RetrofitService

class AppContext : Application() {
    companion object {
        var instance: AppContext? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        RetrofitService.init()
    }
}