package com.pavankumarpatruni.alphabetavatarapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pavankumarpatruni.alpabetavatar.AlphabetAvatar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alphabetAvatar: AlphabetAvatar = findViewById(R.id.alphabetAvatar)
    }
}
