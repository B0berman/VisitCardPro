package com.visitcardpro.api.forms

import android.text.TextUtils
import android.view.View
import com.visitcardpro.R
import com.visitcardpro.views.LoginActivity
import utils.isEmailValid

class LoginFormValidator(var loginActivity: LoginActivity) {
    val email = loginActivity.mEmailView.text.toString()
    val password = loginActivity.mPasswordView.text.toString()
    var valid = true
    var focusView: View? = null

    fun isValidForm(): Boolean {
        // Check for a valid password.
        if (!utils.isPasswordValid(password)) {
            loginActivity.mPasswordView.error = loginActivity.getString(R.string.error_invalid_password)
            focusView = loginActivity.mPasswordView
            valid = false
        } else if (TextUtils.isEmpty(password)) {
            loginActivity.mPasswordView.error = loginActivity.getString(R.string.error_field_required)
            focusView = loginActivity.mPasswordView
            valid = false
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            loginActivity.mEmailView.error = loginActivity.getString(R.string.error_field_required)
            focusView = loginActivity.mEmailView
            valid = false
        } else if (!isEmailValid(email)) {
            loginActivity.mEmailView.error = loginActivity.getString(R.string.error_invalid_email)
            focusView = loginActivity.mEmailView
            valid = false
        }
        return valid
    }
}
