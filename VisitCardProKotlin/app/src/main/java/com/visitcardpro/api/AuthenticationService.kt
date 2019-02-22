package com.visitcardpro.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationService {
    @POST("signup")
    @Headers("Content-Type: application/json")
    fun signUp(@Header("Authorization") credential: String): Call<ResponseBody>

    @POST("signin")
    fun signIn(@Header("Authorization") credential: String): Call<ResponseBody>

    @POST("signout")
    fun signOut(): Call<ResponseBody>

    @GET
    fun refresh(@Header("refresh_token") token: String) : Call<ResponseBody>
}