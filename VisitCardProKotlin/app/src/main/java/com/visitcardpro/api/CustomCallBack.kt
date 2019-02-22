package com.visitcardpro.api

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class CustomCallback<T>(var context: Context, var code: Int): Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {

    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
    }
}