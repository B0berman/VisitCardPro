package com.visitcardpro.viewmodels

import android.app.AlertDialog
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.visitcardpro.api.AuthenticationService
import com.visitcardpro.api.Client
import com.visitcardpro.api.CustomCallback
import retrofit2.Call
import retrofit2.Response
import com.visitcardpro.views.LoginActivity
import com.visitcardpro.views.RegisterActivity
import okhttp3.ResponseBody

class RegisterViewModel(var registerActivity: RegisterActivity): ViewModel() {

    private val authenticationService: AuthenticationService = Client.serviceFactory.getAuthenticationService()
    var registerForm = RegisterForm()
    var mProgressView: View? = null
    var mFormView: View? = null

    private fun attemptRegister() {


        if (registerForm.isValidForm()) {
            utils.showProgress(true, mFormView!!, mProgressView!!)

            Client.auth.email = registerForm.email

            val call = authenticationService
                .signUp(utils.generateAuthorization("${registerForm.email}:${registerForm.password}"))
            call.enqueue(object : CustomCallback<ResponseBody>(registerActivity, 201) {

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) = when(response.code()) {
                    code -> onRegistered()
                    else -> print(response.message())
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    print("---MESSAGE--- : $t.message)")
                    print("---CAUSE--- : ${t.cause})")
                    utils.showProgress(false, mFormView!!, mProgressView!!)
                    val builder = AlertDialog.Builder(registerActivity)
                    builder.setMessage("$t.cause - $t.message")
                    builder.setCancelable(true)
                    builder.setPositiveButton("Ok") { dialog, _ -> dialog.cancel() }
                    val alert = builder.create()
                    alert.show()

                }
            })
        } else
            Toast.makeText(registerActivity, "FORM ERROR", Toast.LENGTH_SHORT).show()
//        registerFormValidator.focusView!!.requestFocus()
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

    fun RegisterButtonClicked() {
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