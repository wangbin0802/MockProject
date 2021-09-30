package com.stareme.ocbcsimple.http.model

import androidx.annotation.Keep

@Keep
data class TransferResponse(val status: String, val description: String?) {
    fun success(): Boolean {
        return status == "success"
    }
}
