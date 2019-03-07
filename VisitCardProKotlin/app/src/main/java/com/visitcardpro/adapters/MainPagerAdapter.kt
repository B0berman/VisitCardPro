package com.visitcardpro.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.visitcardpro.views.CardFragment
import com.visitcardpro.views.ContactFragment
import java.util.ArrayList

class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private var fragments: ArrayList<Fragment> = object : ArrayList<Fragment>() {
        init {
            add(CardFragment())
            add(ContactFragment())
        }
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}