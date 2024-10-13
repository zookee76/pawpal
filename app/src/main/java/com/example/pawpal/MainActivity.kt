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

            Button(
                onClick = {
                    // Test the Home Dashboard only delete when main pages are available
                    startActivity(Intent(this@MainActivity, phomedashboard::class.java))
                }
            ) {
                Text(text = "Home Dashboard")
            }

            Button(
                onClick = {
                    // Test the Pet Login only delete when main pages are available
                    startActivity(Intent(this@MainActivity, pregister::class.java))
                }
            ) {
                Text(text = "Pet Register")
            }

            Button(
                onClick = {
                    // Test the Clinic Login only delete when main pages are available
                    startActivity(Intent(this@MainActivity, cregister::class.java))
                }
            ) {
                Text(text = "Clinic Register")
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMainScreen() {
        MainScreen()
    }
}
