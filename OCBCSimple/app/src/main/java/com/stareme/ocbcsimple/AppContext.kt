package com.stareme.ocbcsimple

import android.app.Application
import com.stareme.ocbcsimple.http.RetrofitService

class AppContext : Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitService.init()
    }
}