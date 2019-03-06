package com.visitcardpro.database

 import android.arch.persistence.room.Delete
 import android.arch.persistence.room.Query
 import com.visitcardpro.models.PersonnalCard
import com.visitcardpro.models.SharedCard
import retrofit2.Call

interface CardDao {

    @Delete
    fun removeCardBy(key: String)

    @Query("")
    fun getContact(key: String): Call<SharedCard>

    fun exploreCards() : Call<List<PersonnalCard>>

    fun createCard()


    fun updateCard(key: String) : Call<PersonnalCard>
}