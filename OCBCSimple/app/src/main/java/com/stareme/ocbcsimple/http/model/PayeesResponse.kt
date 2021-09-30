package com.stareme.ocbcsimple.http.model

import androidx.annotation.Keep

@Keep
data class PayeesResponse(val status: String, val data: ArrayList<Payee>)

@Keep
data class Payee(val id: String, val accountNo: String, val accountHolderName: String)
