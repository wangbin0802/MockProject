package com.stareme.ocbcsimple.utils

import android.content.Context
import android.content.Intent
import android.util.Log

fun Context.safeStartActivity(intent: Intent) {
    try {
        startActivity(intent)
    } catch (e: Exception) {
        // We need to prevent crash, but we can do nothing about this exception
        Log.e(intent.action, "startActivity Exception", e)
    }
}