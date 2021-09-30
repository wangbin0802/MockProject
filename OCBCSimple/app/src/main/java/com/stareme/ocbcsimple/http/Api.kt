package com.stareme.ocbcsimple.http

import com.stareme.ocbcsimple.http.model.BodyLogin
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {
    @POST("authenticate/login")
    fun login(@Body body: BodyLogin?): Observable<ResponseBody?>?
}