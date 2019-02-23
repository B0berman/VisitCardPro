package com.visitcardpro.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.view.MenuItem
import com.visitcardpro.api.adapters.MainPagerAdapter
import com.visitcardpro.R



class MainActivity : AppCompatActivity() {
    private var prevMenuItem: MenuItem? = null
    private lateinit var mNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_card -> {
                    viewPager.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_contact -> {
                    viewPager.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }
    private val mPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }
        override fun onPageScrollStateChanged(state: Int) {

        }

        override fun onPageSelected(position: Int) {
            if (prevMenuItem != null) {
                prevMenuItem!!.isChecked = false
            } else {
                mNavigationView.menu.getItem(0).isChecked = false
            }

            mNavigationView.menu.getItem(position).isChecked = true
            prevMenuItem = mNavigationView.menu.getItem(position)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavigationView = findViewById(R.id.navigation)
        viewPager = findViewById(R.id.viewpager)

        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewPager.addOnPageChangeListener(mPageChangeListener)
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
    }
}

