package com.guagua.googletrendsvisualize.googletrends

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.guagua.googletrendsvisualize.R
import kotlinx.android.synthetic.main.activity_google_trends.*

class GoogleTrendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_trends)
        setSupportActionBar(toolbar)
        supportFragmentManager.beginTransaction().add(R.id.container, GoogleTrendsFragment.getInstance()).commit()

    }
}
