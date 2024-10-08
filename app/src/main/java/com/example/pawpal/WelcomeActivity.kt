package com.example.pawpal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.em
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Welcome()
        }
    }
}

@Composable
fun Welcome(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(430.dp)
            .requiredHeight(932.dp)
            .background(color = Color(0xfffffbec))
    ) {
        Tab(
            selected = false,
            onClick = {  },
            text = {
                Text(
                    text = "NEXT",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .wrapContentHeight(align = Alignment.CenterVertically))
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 96.dp, y = 832.dp)
                .requiredWidth(238.dp)
                .requiredHeight(50.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = Color(0xff0c439e))
                .padding(horizontal = 16.dp, vertical = 7.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 33.dp, y = 181.dp)
                .requiredWidth(363.dp)
                .requiredHeight(119.dp)
        ) {
            Text(
                text = "Welcome to PawPal!",
                color = Color(0xfffbaf41),
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 34.sp),
                modifier = Modifier
                    .requiredWidth(363.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
            Text(
                text = "Your all-in-one app for managing your pet’s health, appointments, and medical records. ",
                color = Color.Black.copy(alpha = 0.5f),
                textAlign = TextAlign.Center,
                lineHeight = 8.24.em,
                style = TextStyle(fontSize = 17.sp, letterSpacing = 0.2.sp),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 0.dp, y = 47.dp)
                    .requiredWidth(363.dp)
                    .wrapContentHeight(align = Alignment.CenterVertically)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.fiimage),
            contentDescription = "Pet Image",
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = 62.dp, y = 380.dp)
        )
    }
}

@Composable
fun Image(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .requiredWidth(305.dp)
            .requiredHeight(303.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(color = Color(0xffd7d9e0))
    ) {
        Image(
            painter = painterResource(id = R.drawable.fiimage),
            contentDescription = "fi:image",
            modifier = Modifier
                .requiredSize(30.dp)
        )
    }
}

@Preview(widthDp = 430, heightDp = 932)
@Composable
private fun WelcomePreview() {
    Welcome(Modifier)
}
