package com.tunc.kotlinmvpretro.Activities.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tunc.kotlinmvpretro.R
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val id = intent.getStringExtra("id")

        tv_id.text = id

    }
}
