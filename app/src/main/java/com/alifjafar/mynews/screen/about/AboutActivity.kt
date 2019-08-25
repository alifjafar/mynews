package com.alifjafar.mynews.screen.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.alifjafar.mynews.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.title = getString(R.string.about_me)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
