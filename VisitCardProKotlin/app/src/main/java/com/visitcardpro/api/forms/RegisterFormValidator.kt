package com.visitcardpro.api.forms

import android.text.TextUtils
import android.view.View
import com.visitcardpro.R
import com.visitcardpro.views.RegisterActivity
import utils.isEmailValid

class RegisterFormValidator(var registerActivity: RegisterActivity) {
    val email = registerActivity.mEmailView.text.toString()
    val password = registerActivity.mPasswordView.text.toString()
    var valid = true
    var focusView: View? = null

    fun isValidForm(): Boolean {
        // Check for a valid password.
        if (!utils.isPasswordValid(password)) {
            registerActivity.mPasswordView.error = registerActivity.getString(R.string.error_invalid_password)
            focusView = registerActivity.mPasswordView
            valid = false
        } else if (TextUtils.isEmpty(password)) {
            registerActivity.mPasswordView.error = registerActivity.getString(R.string.error_field_required)
            focusView = registerActivity.mPasswordView
            valid = false
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            registerActivity.mEmailView.error = registerActivity.getString(R.string.error_field_required)
            focusView = registerActivity.mEmailView
            valid = false
        } else if (!isEmailValid(email)) {
            registerActivity.mEmailView.error = registerActivity.getString(R.string.error_invalid_email)
            focusView = registerActivity.mEmailView
            valid = false
        }
        return false
    }
}
