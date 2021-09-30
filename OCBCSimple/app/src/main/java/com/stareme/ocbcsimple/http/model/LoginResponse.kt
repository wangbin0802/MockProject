package com.stareme.ocbcsimple.http.model

import androidx.annotation.Keep

@Keep
data class LoginResponse(val status: String, val token: String)
