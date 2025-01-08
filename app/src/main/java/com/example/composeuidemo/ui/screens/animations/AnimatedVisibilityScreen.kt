package com.example.composeuidemo.ui.screens.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeuidemo.R
import com.example.composeuidemo.utils.REPO_BASE_URL

val url = "$REPO_BASE_URL/ui/screens/animations/AnimatedVisibilityScreen.kt"

@Composable
fun AnimatedVisibilityScreen() {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(enabled = true, state = rememberScrollState())
    ) {

        CustomizeAnimationDemo("Default")



        CustomizeAnimationDemo(
            "Fade in, Fade out",
            enterTransition = fadeIn(),
            exitTransition = fadeOut()
        )


        CustomizeAnimationDemo(
            "Scale in, Scale out",
            enterTransition = scaleIn(),
            exitTransition = scaleOut()
        )


        // 加上 fade 渐变效果好一点
        CustomizeAnimationDemo(
            "Slide in ↘ + Fade, Scale out + Fade",
            enterTransition = fadeIn(tween(durationMillis = 2000)) + slideIn(
                animationSpec = tween(durationMillis = 2000),
                initialOffset = { it: IntSize -> IntOffset(-it.width, -it.height) }),
            exitTransition = fadeOut(tween(durationMillis = 1500)) + slideOut(
                animationSpec = tween(durationMillis = 2000),
                targetOffset = { it: IntSize -> IntOffset(it.width, it.height) }),

            )
        CustomizeAnimationDemo(
            "expand, Shrink out",
            enterTransition = expandIn(),
            exitTransition = shrinkOut()
        )


    }
}

// 每一个 card 展示一种动画效果
@Composable
fun CustomizeAnimationDemo(
    name: String = "Default",
    enterTransition: EnterTransition? = null,
    exitTransition: ExitTransition? = null
) {
    var visible by remember { mutableStateOf(true) }

    // 每个demo都是一个 card，里面第一行是标题的链接，第二行是图片区域
    Card(
        onClick = {
            visible = !visible
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val uriHandler = LocalUriHandler.current

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = name, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                IconButton(onClick = {
                    uriHandler.openUri(url)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Source code"
                    )
                }

            }

            Row(
                Modifier
                    .padding(5.dp).border(1.dp, Color.Red).padding(5.dp)
                    .wrapContentSize(), horizontalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = visible, label = name,
                    enter = enterTransition ?: (fadeIn() + expandHorizontally()),
                    exit = exitTransition ?: (fadeOut() + shrinkHorizontally())
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Animated Logo",
                        modifier = Modifier.wrapContentSize(),
                        contentScale = ContentScale.Fit    // keep the image's aspect ratio
                    )
                }

            }


        }
    }
}