package com.konstantinosreppas.randomshaperlibrary

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.konstantinosreppas.randomshaper.RandomShaper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        image.setOnClickListener {
            text.visibility = View.GONE
            RandomShaper(image, Color.BLACK, 5) }
    }
}
