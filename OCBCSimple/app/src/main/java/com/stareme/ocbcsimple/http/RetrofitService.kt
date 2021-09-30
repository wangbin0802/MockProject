package com.stareme.ocbcsimple.http

import com.stareme.ocbcsimple.BuildConfig
import io.reactivex.schedulers.Schedulers

import io.reactivex.schedulers.Schedulers.io
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSession

object RetrofitService {
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: Api

    fun init() {
        initOkhttp()

        val observeOn = Schedulers.computation()
        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.15:8080/")
            .client(okHttpClient)
            .addCallAdapterFactory(ObserveOnMainCallAdapterFactory(observeOn))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(io()))
            .build()

        api = retrofit.create(Api::class.java)
    }

    private fun initOkhttp() {
        val builder = OkHttpClient.Builder()
        // Log http to console.
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        builder.hostnameVerifier { _: String?, _: SSLSession? ->
            true //allow all host
        }

        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)

        okHttpClient = builder.build()
    }
}