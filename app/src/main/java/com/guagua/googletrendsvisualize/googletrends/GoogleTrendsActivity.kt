package com.guagua.googletrendsvisualize.googletrends

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.guagua.googletrendsvisualize.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_google_trends.*
import javax.inject.Inject

class GoogleTrendsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var googleTrendsFragment: GoogleTrendsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_trends)
        setSupportActionBar(toolbar)

        var fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment == null){
            fragment = googleTrendsFragment
        }
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
    }
}
