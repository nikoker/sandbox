package com.getbux.sandbox

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.main_view_pager)
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount() = 3

            override fun getItem(position: Int): Fragment? {
                return when (position) {
                    0 -> MainFragment.newInstance("GOOGLE", Color.WHITE)
                    1 -> MainFragment.newInstance("APPLE", Color.GRAY)
                    2 -> MainFragment.newInstance("FACEBOOK", Color.BLUE)
                    else -> null
                }
            }
        }
    }
}
