package com.visitcardpro.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

const val auth_url: String = "auth/"

interface AuthenticationService {
    @POST("${auth_url}signup")
    fun signUp(@Header("Authorization") credential: String): Call<ResponseBody>

    @POST("${auth_url}signin")
    fun signIn(@Header("Authorization") credential: String): Call<ResponseBody>

    @POST("${auth_url}signout")
    fun signOut(): Call<ResponseBody>

    @GET(auth_url)
    fun refresh(@Header("refresh_token") token: String) : Call<ResponseBody>

    @GET("${auth_url}test")
    fun test(): Call<ResponseBody>
}