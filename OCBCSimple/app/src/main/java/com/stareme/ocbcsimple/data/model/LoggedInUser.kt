package com.stareme.ocbcsimple.data.model

import androidx.annotation.Keep

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Keep
data class LoggedInUser(
    val userId: String,
    val displayName: String,
    val userToken: String
)