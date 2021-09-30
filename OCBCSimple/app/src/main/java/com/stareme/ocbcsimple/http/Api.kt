package com.stareme.ocbcsimple.http

import com.stareme.ocbcsimple.http.model.*
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface Api {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("authenticate/login")
    fun login(@Body body: BodyLogin): Observable<LoginResponse>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("account/balances")
    fun balance(@Header("Authorization") token: String): Observable<BalanceResponse>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("account/transactions")
    fun transactions(@Header("Authorization") token: String): Observable<TransactionResponse>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("account/payees")
    fun payees(@Header("Authorization") token: String): Observable<PayeesResponse>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("transfer")
    fun transfer(@Header("Authorization") token: String, @Body body: TransferBody): Observable<TransferResponse>
}