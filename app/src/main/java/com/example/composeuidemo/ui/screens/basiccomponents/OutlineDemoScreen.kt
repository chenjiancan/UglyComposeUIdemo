package com.example.composeuidemo.ui.screens.basiccomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

// todo: 把虚线边框做出 Modifier 属性 util
@Composable
fun OutlineDemoScreen() {


    // border with dash line
    Box(modifier = Modifier.background(Color.LightGray).fillMaxWidth().padding(10.dp)
        .drawBehind {
            drawRoundRect(
                color = Color.Blue,
                style = Stroke(
                    width = 1f,
                    pathEffect = PathEffect.dashPathEffect(intervals = floatArrayOf(10f, 10f),  phase = 0f)
                )
            )
        }
    ) {
        Text("This is a text! 😀",
            modifier = Modifier.padding(10.dp)
        )
    }
}
