package com.guagua.googletrendsvisualize.googletrends

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.guagua.googletrendsvisualize.R
import kotlinx.android.synthetic.main.activity_google_trends.*

class GoogleTrendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_trends)
        setSupportActionBar(toolbar)

        var googleTrendsFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (googleTrendsFragment == null){
            googleTrendsFragment = GoogleTrendsFragment.getInstance()
        }
        supportFragmentManager.beginTransaction().add(R.id.container, googleTrendsFragment).commit()
    }
}