package com.stareme.ocbcsimple.http.model

import androidx.annotation.Keep

@Keep
data class BalanceResponse(val status: String, val balance: String) {
    fun success(): Boolean {
        return status == "success"
    }
}