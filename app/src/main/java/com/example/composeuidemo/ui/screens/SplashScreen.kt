package com.example.composeuidemo.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import com.example.composeuidemo.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navToHome: () -> Unit) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        Log.d("SplashScreen", "LaunchedEffect: Start")

        isVisible = true
        delay(1000)

        // Navigate to the next screen
        isVisible = false
        delay(300)
        Log.d("SplashScreen", "LaunchedEffect: navToHome")
        navToHome()
        Log.d("SplashScreen", "LaunchedEffect: Done")
    }

    Box(Modifier.fillMaxSize().clickable(enabled = true, onClick = {
        Log.d("SplashScreen", "Click Skip SplashScreen and navToHome manually")
        navToHome()
    }), contentAlignment = Alignment.Center) {

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(tween(800)) + slideIn(
                tween(800),
                initialOffset = { it -> IntOffset(0, -it.height) }),
            exit = fadeOut(tween(500)),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.wrapContentSize(),
                contentScale = ContentScale.Fit    // keep the image's aspect ratio
            )
        }
    }
}