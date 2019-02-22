package com.visitcardpro.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceFactory {
    private val BASE_URI = "http://92.222.82.30:8080/visitcardpro/"

    fun getAuthenticationService(): AuthenticationService = buildService().create(AuthenticationService::class.java)

    fun getContactService(): ContactService = buildService().create(ContactService::class.java)

    fun getCardService(): CardService = buildService().create(CardService::class.java)

    private fun buildService(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URI)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}