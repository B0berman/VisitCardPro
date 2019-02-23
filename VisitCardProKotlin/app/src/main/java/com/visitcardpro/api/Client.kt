package com.visitcardpro.api

import android.app.Activity
import android.content.Intent
import com.visitcardpro.models.Authentication
import com.visitcardpro.views.LoginActivity

object Client {
    internal var auth: Authentication = Authentication("", "")
    internal val serviceFactory: ServiceFactory = ServiceFactory()

    fun signOut(activity: Activity) {
        auth.email = ""
        auth.accessToken = ""
        val launchNextActivity = Intent(activity, LoginActivity::class.java)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(launchNextActivity)
        activity.finish()
    }
}