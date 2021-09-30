package com.stareme.ocbcsimple.http

import com.stareme.ocbcsimple.http.model.BodyLogin
import com.stareme.ocbcsimple.http.model.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("authenticate/login")
    fun login(@Body body: BodyLogin): Observable<LoginResponse>
}