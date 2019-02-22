package com.visitcardpro.viewmodels

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.visitcardpro.views.MainActivity
import com.visitcardpro.api.AuthenticationService
import com.visitcardpro.api.Client
import com.visitcardpro.api.CustomCallback
import okhttp3.Headers
import retrofit2.Call
import retrofit2.Response
import com.visitcardpro.api.forms.LoginFormValidator
import com.visitcardpro.views.LoginActivity
import com.visitcardpro.views.RegisterActivity
import okhttp3.ResponseBody

class LoginViewModel(var loginActivity: LoginActivity): ViewModel() {

    private val authenticationService: AuthenticationService = Client.serviceFactory.getAuthenticationService()

    private fun attemptLogin() {

        var loginFormValidator = LoginFormValidator(loginActivity)

        if (loginFormValidator.isValidForm()) {
            loginActivity.showProgress(true)

            Client.auth.email = loginFormValidator.email

            val call = authenticationService
                .signIn(utils.generateAuthorization("${loginFormValidator.email}:${loginFormValidator.password}"))
            call.enqueue(object : CustomCallback<ResponseBody>(loginActivity, 202) {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) = when(response.code()) {
                    code -> onSuccessLog(response.headers())
                    else -> print(response.message())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    onConnected()
//                    error("KO")
                }
            })
        } else
            loginFormValidator.focusView!!.requestFocus()
    }

    private fun onConnected() {
        val launchNextActivity = Intent(loginActivity, MainActivity::class.java)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        loginActivity.startActivity(launchNextActivity)
    }

    fun onSuccessLog(headers: Headers) {
        Client.auth.accessToken = headers.get("access_token")
        var refreshToken = headers.get("refresh_token")
        when (refreshToken) {
            "" -> error("failed to connect")
            else -> {
                utils.savePreferences("refresh_token", refreshToken, loginActivity)
                onConnected()
            }
        }

    }

    fun getNoAccountButtonListener() =  View.OnClickListener {
        val launchNextActivity = Intent(loginActivity, RegisterActivity::class.java)
        loginActivity.startActivity(launchNextActivity)    }

    fun getLoginButtonListener() =  View.OnClickListener {
        attemptLogin()
    }

    fun getPasswordEditorActionListener() = TextView.OnEditorActionListener { _, id, _ ->
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            this.attemptLogin()
            return@OnEditorActionListener true
        }
        false
    }
}