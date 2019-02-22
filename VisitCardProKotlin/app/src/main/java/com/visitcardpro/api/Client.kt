package com.visitcardpro.api

import com.visitcardpro.models.Authentication

object Client {
    internal var auth: Authentication = Authentication("", "")
    internal val serviceFactory: ServiceFactory = ServiceFactory()

    fun signOut() {

    }
}