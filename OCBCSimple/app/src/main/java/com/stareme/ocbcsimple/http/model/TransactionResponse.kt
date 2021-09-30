package com.stareme.ocbcsimple.http.model

import androidx.annotation.Keep

@Keep
data class TransactionResponse(val status: String, val data: ArrayList<Order>)

@Keep
data class Order(val id: String, val type: String, val amount: Double, val currency: String,
                 val from: Payee, val description: String, val date: String)
