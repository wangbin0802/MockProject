package com.stareme.ocbcsimple.http.model

import androidx.annotation.Keep

@Keep
data class BodyLogin(val username: String, val password: String)