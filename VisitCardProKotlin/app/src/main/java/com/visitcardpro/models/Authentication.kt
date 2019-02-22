package com.visitcardpro.models

import java.io.Serializable

data class Authentication(var email: String, var accessToken: String) : Serializable