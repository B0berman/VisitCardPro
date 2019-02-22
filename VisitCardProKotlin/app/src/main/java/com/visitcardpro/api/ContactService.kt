package com.visitcardpro.api

import com.visitcardpro.models.SharedCard
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

const val contact_url: String = "contacts"

interface ContactService {

    @POST(contact_url)
    fun addContact(@Header("access_token") token: String = utils.getAccessToken(), @Query("sharingKey") key: String): Call<SharedCard>

    @GET(contact_url)
    fun getContacts(@Header("access_token") token: String = utils.getAccessToken(), @Header("Authorization") credential: String): Call<List<SharedCard>>

    @DELETE(contact_url)
    fun removeContact(@Header("access_token") token: String = utils.getAccessToken(), @Query("sharingKey") key: String): Call<ResponseBody>

    @POST("${contact_url}/{key}")
    fun getContact(@Header("access_token") token: String = utils.getAccessToken(), @Path("key") key: String): Call<SharedCard>
}