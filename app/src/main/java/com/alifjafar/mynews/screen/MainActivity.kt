package com.alifjafar.mynews.screen

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.alifjafar.mynews.R
import com.alifjafar.mynews.screen.article.fragment.BitcoinFragment
import com.alifjafar.mynews.screen.article.fragment.EverythingFragment
import com.alifjafar.mynews.screen.article.fragment.TopHeadlineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.title_main)
        pager.adapter = MainPagerAdapter(supportFragmentManager)
        tab.setupWithViewPager(pager)

    }


    inner class MainPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        private val fragment = listOf(
            TopHeadlineFragment() to getString(R.string.title_top_headline),
            EverythingFragment() to getString(R.string.title_everything),
            BitcoinFragment() to getString(R.string.title_bitcoin)
        )

        override fun getItem(position: Int): Fragment = fragment[position].first

        override fun getCount(): Int = fragment.size

        override fun getPageTitle(position: Int): CharSequence? = fragment[position].second
    }
}
