package com.stareme.ocbcsimple.http.model

import androidx.annotation.Keep

@Keep
data class PayeesResponse(val status: String, val data: ArrayList<Payee>?, val description: String?) {
    fun success(): Boolean {
        return status == "success"
    }
}

@Keep
data class Payee(val id: String, val accountNo: String, val accountHolderName: String)
