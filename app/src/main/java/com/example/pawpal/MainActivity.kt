package com.example.pawpal

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.pawpal.Welcome.welcome

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, welcome::class.java))
        finish()
    }
}
