package com.example.composeuidemo.ui.screens.basiccomponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File

@Composable
fun TextDemoScreen() {

    // 展示两个使用 Text 的例子，一个是最简单的显示文本，一个是使用 Text 的属性来显示样式
    Column(modifier = Modifier.fillMaxSize()) {

        // box with dashed blue border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(0.5.dp, Color.Blue, RoundedCornerShape(10))
                .padding(10.dp)
        ) {
            BasicText(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.inversePrimary)) {
                    append("This is a text! 😀")
                }
            })
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(0.5.dp, Color.Blue, RoundedCornerShape(10))
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            var textColorHue by remember { mutableFloatStateOf(0.5f) }
            var textColor = Color.hsv(textColorHue * 360f, 1f, 1f)

            var fontSize by remember { mutableFloatStateOf(10f) }


            Text(
                "This is a text! 😀",
                color = textColor,
                fontSize = fontSize.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Text Color: ")

                Slider(value = textColorHue, onValueChange = {
                    textColorHue = it
                })

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Font size: ")

                Slider(value = fontSize, valueRange = 8f..30f, onValueChange = {
                    fontSize = it
                })

            }

        }


        // todo: AnnotatedString 文本样式
    }
}