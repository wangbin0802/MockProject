package com.stareme.ocbcsimple.http.model

import androidx.annotation.Keep

@Keep
data class TransferBody(val recipientAccountNo: String, val amount: Double,
                        val date: String, val description: String)
