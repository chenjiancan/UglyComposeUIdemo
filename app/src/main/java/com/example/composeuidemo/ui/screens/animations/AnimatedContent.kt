package com.example.composeuidemo.ui.screens.animations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.composeuidemo.ui.screens.myutils.BorderedBox
import com.example.composeuidemo.R

@Composable
fun AnimatedContentScreen() {

    var contentFlag by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { contentFlag = !contentFlag }) {
            Text(text = "Toggle Visibility")
        }
        BorderedBox(
            modifier = Modifier
                .fillMaxSize()   // Fix the height to 1/3 of the screen
                  // Make the width intrinsic to the content, the image's contentScale is Fit,
            // so we can keep the image's aspect ratio and dynamically wrap its width
        ) {
            AnimatedContent(targetState = contentFlag,
                transitionSpec = {
                    if (targetState) {
//                        fadeIn(tween(durationMillis = 2000)) togetherWith  fadeOut(tween(durationMillis = 2000))
                        scaleIn(tween(durationMillis = 2000))+fadeIn() togetherWith fadeOut() + scaleOut(tween(durationMillis = 2000))
                    } else {
                        expandIn(tween(durationMillis = 2000)) togetherWith shrinkOut(tween(durationMillis = 2000))
                    }

                }){ targetState ->
                if (targetState) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1 / 3f)
                            .background(Color.Red)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1 / 3f)
                            .background(Color.Green)
                    )

                }

            }
        }
    }
}