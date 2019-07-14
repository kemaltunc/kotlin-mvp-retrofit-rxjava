package com.tunc.kotlinmvpretro.Retrofit


import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


data class Result(
    val success: Boolean,
    val message: String,
    val id: String
)

interface Api {
    @FormUrlEncoded
    @POST("register")
    fun createUser(@FieldMap params: Map<String, String>): Observable<Result>


    @FormUrlEncoded
    @POST("login")
    fun login(@FieldMap params: Map<String, String>): Observable<Result>

    companion object Factory {
        fun create(): Api {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.1.23:3000/")
                .build()

            return retrofit.create(Api::class.java);
        }
    }


}