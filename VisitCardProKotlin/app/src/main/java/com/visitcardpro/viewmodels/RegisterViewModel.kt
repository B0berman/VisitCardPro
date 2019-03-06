package com.visitcardpro.viewmodels

import android.app.AlertDialog
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.visitcardpro.api.AuthenticationService
import com.visitcardpro.api.Client
import com.visitcardpro.api.CustomCallback
import retrofit2.Call
import retrofit2.Response
import com.visitcardpro.api.forms.RegisterFormValidator
import com.visitcardpro.views.LoginActivity
import com.visitcardpro.views.RegisterActivity
import okhttp3.ResponseBody

class RegisterViewModel(var registerActivity: RegisterActivity): ViewModel() {

    private val authenticationService: AuthenticationService = Client.serviceFactory.getAuthenticationService()

    private fun attemptRegister() {

        var registerFormValidator = RegisterFormValidator(registerActivity)

        if (registerFormValidator.isValidForm()) {
            registerActivity.showProgress(true)

            Client.auth.email = registerFormValidator.email

            val call = authenticationService
                .signUp(utils.generateAuthorization("${registerFormValidator.email}:${registerFormValidator.password}"))
            print("caallllllll")
            call.enqueue(object : CustomCallback<ResponseBody>(registerActivity, 201) {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) = when(response.code()) {
                    code -> onRegistered()
                    else -> print(response.message())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    print("---MESSAGE--- : $t.message)")
                    print("---CAUSE--- : ${t.cause})")
                    registerActivity.showProgress(false)
                    val builder = AlertDialog.Builder(registerActivity)
                    builder.setMessage("$t.cause - $t.message")
                    builder.setCancelable(true)
                    builder.setPositiveButton("Ok") { dialog, _ -> dialog.cancel() }
                    val alert = builder.create()
                    alert.show()

                }
            })
        } else
            registerFormValidator.focusView!!.requestFocus()
    }

    private fun onRegistered() {
        val launchNextActivity = Intent(registerActivity, LoginActivity::class.java)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        registerActivity.startActivity(launchNextActivity)
        registerActivity.finish()
    }

    fun getRegisterButtonListener() =  View.OnClickListener {
        attemptRegister()
    }

    fun getPasswordEditorActionListener() = TextView.OnEditorActionListener { _, id, _ ->
        if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
            this.attemptRegister()
            return@OnEditorActionListener true
        }
        false
    }
}