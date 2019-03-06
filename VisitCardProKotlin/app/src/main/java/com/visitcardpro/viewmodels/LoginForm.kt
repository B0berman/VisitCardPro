package viewmodels

import android.text.TextUtils
import android.view.View
import utils.isEmailValid

class LoginForm {
    var email: String = ""
    var password: String = ""

    fun isValidForm(): Boolean {
        var valid: Boolean = true
        // Check for a valid password.
        if (!utils.isPasswordValid(password)) {
            valid = false
        } else if (TextUtils.isEmpty(password)) {
            valid = false
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            valid = false
        } else if (!isEmailValid(email)) {
//            mEmailView.error = getString(R.string.error_invalid_email)
//            focusView = mEmailView
            valid = false
        }
        return valid
    }
}