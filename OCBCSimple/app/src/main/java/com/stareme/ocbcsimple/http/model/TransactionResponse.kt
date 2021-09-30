package com.stareme.ocbcsimple.http.model

import androidx.annotation.Keep

@Keep
data class TransactionResponse(val status: String, val data: ArrayList<Order>) {
    fun success(): Boolean {
        return status == "success"
    }
}

@Keep
data class Order(val id: String, val type: String, val amount: Double, val currency: String,
                 val from: Payee, val description: String, val date: String) {
    fun getFormatDate(): String {
        val timeSplit = date.split("T")
        val secondSplit = timeSplit[1].split(":")
        return "${timeSplit[0]} ${secondSplit[0]}:${secondSplit[1]}"
    }

    fun isReceived(): Boolean {
        return type == "receive"
    }
}
