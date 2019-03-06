package com.visitcardpro.viewmodels

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.visitcardpro.R
import com.visitcardpro.models.Card


class FCardViewModel(var activity: Activity, var cards: List<Card>):  RecyclerView.Adapter<FCardViewModel.CardHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, type: Int): FCardViewModel.CardHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false);

        return CardHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(holder: FCardViewModel.CardHolder, position: Int) {
        val card: Card = cards[position]

        holder.emailView.text = card.email
        holder.phoneView.text = card.phone
        holder.firstNameView.text = card.firstName
        holder.lastNameView.text = card.lastName
    }

    fun updateContent(cards: List<Card>) {
            this.cards = cards
            notifyDataSetChanged()
    }

    class CardHolder(itemView: View) : ViewHolder(itemView) {
        var emailView: TextView = itemView.findViewById(R.id.email_view)
        var phoneView: TextView = itemView.findViewById(R.id.phone_view)
        var lastNameView: TextView = itemView.findViewById(R.id.lastName_view)
        var firstNameView: TextView = itemView.findViewById(R.id.firstName_view)

    }}
