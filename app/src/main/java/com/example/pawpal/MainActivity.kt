package com.example.pawpal

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        Column {
            Button(
                onClick = {
                    // Intent to launch WelcomeActivity
                    startActivity(Intent(this@MainActivity, welcome::class.java))
                }
            ) {
                Text(text = "Go to Welcome Screen")
            }

        }
    }

    @Preview
    @Composable
    fun PreviewMainScreen() {
        MainScreen()
    }
}
