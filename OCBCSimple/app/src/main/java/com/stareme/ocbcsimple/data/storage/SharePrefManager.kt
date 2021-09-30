package com.stareme.ocbcsimple.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.stareme.ocbcsimple.AppContext

object SharePrefManager {
    private val sp: SharedPreferences = AppContext.instance!!.getSharedPreferences("user_info", Context.MODE_PRIVATE)

    const val USER_TOKEN_KEY = "key_user_token"

    fun putString(key: String, value: String?) {
        sp.edit().putString(key, value).apply()
    }

    fun putInt(key: String, value: Int) {
        sp.edit().putInt(key, value).apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }

    fun getString(key: String): String? {
        return sp.getString(key, null)
    }
}