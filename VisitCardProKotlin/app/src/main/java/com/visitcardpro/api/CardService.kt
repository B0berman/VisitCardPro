package com.visitcardpro.api

import com.visitcardpro.models.PersonnalCard
import com.visitcardpro.models.SharedCard
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

const val card_url: String = "cards"

interface CardService {
    @DELETE("$card_url/{key}")
    fun removeCard(@Header("access_token") token: String = utils.getAccessToken(), @Path("sharingKey") key: String): Call<ResponseBody>

    @POST("$card_url/{key}")
    fun getContact(@Header("access_token") token: String = utils.getAccessToken(), @Path("key") key: String): Call<SharedCard>

    @GET
    fun exploreCards(@Header("access_token") token: String = utils.getAccessToken()) : Call<List<PersonnalCard>>

    @POST
    fun createCard(@Header("access_token") token: String = utils.getAccessToken()) : Call<PersonnalCard>

    @PUT
    fun updateCard(@Header("access_token") token: String = utils.getAccessToken(), @Path("$card_url/{key}") key: String) : Call<PersonnalCard>
}

