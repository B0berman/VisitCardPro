package com.visitcardpro.models

import android.arch.persistence.room.Entity
import java.io.Serializable

@Entity
data class Card(var email: String, var phone: String, var firstName: String, var lastName: String): Serializable