package com.visitcardpro.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.visitcardpro.R
import com.visitcardpro.viewmodels.FCardViewModel
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


class CardFragment: Fragment() {

    private val viewModel: FCardViewModel = FCardViewModel(this.activity!!, emptyList())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.cardRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }
}