package com.example.composeuidemo.ui.screens.layout

import androidx.compose.foundation.DefaultMarqueeIterations
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode.Companion.Immediately
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeuidemo.utils.REPO_BASE_URL

val url = "$REPO_BASE_URL/ui/screens/layout/AlignmentArrangementScreen.kt"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AlignmentArrangementScreen() {
    // dynamic change arrangement option by radio button by user
    val arrangementOption = listOf(
        Arrangement.Start,
        Arrangement.Center,
        Arrangement.End,
        Arrangement.SpaceBetween,
        Arrangement.SpaceAround,
        Arrangement.SpaceEvenly
    )
    var horizontalArrangement by remember { mutableStateOf(Arrangement.Start) }

    val verticalAlignmentOption = listOf(
        Alignment.Top,
        Alignment.CenterVertically,
        Alignment.Bottom
    )

    var verticalAlignment by remember { mutableStateOf(Alignment.CenterVertically) }


    Column(
        modifier = Modifier
            .fillMaxSize(),
//            .verticalScroll(scrollState, enabled = true),
        verticalArrangement = Arrangement.Top
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Alignment & Arrangement", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            val uriHandler = LocalUriHandler.current
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Source code",
                modifier = Modifier.clickable(enabled = true, onClick = {
                    uriHandler.openUri(url)
                }).size(30.dp)
            )

        }

//        val arrangementName = horizontalArrangement.toString().split("#")[1]
//        Text("Arrangement: $arrangementName")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = verticalAlignment,
            horizontalArrangement = horizontalArrangement
        ) {
            Box(
                Modifier
                    .height(100.dp)
                    .width(50.dp)
                    .background(Color.Blue)
            ) { }
            Box(
                Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .background(Color.Red)
            ) { }
            Box(
                Modifier
                    .height(25.dp)
                    .width(50.dp)
                    .background(Color.Green)
            ) { }

        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .padding(10.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            var selectedOption by remember { mutableStateOf(0) }
            Text("Arrangement")

            // 使用 SingleChoiceSegmentedButtonRow 显示选项
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
            ) {
//                SegmentedButton
                arrangementOption.forEachIndexed { index, item ->
                    SegmentedButton(modifier = Modifier.height(IntrinsicSize.Max),
                        selected = selectedOption == index, onClick = {
                            selectedOption = index
                            horizontalArrangement = arrangementOption.get(index)
                        }, shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = arrangementOption.size
                        ), label = {
                            Text(
                                item.toString().split("#")[1],
                                fontSize = 8.sp,
                                maxLines = 1,
                                modifier = Modifier.basicMarquee(
                                    1000,  //how many times to repeat
                                    animationMode = Immediately,
                                    delayMillis = 300
                                )
                            )

                        })

                }
            }
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .padding(10.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            var selectedOption by remember { mutableStateOf(0) }
            Text("Alignment")
            // 使用 SingleChoiceSegmentedButtonRow 显示选项
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.fillMaxWidth()
            ) {
//                SegmentedButton
                verticalAlignmentOption.forEachIndexed { index, item ->
                    SegmentedButton(selected = selectedOption == index, onClick = {
                        selectedOption = index
                        verticalAlignment = verticalAlignmentOption.get(index)

                    }, shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = verticalAlignmentOption.size
                    ), label = {
                        Text(
                            item.toString(), fontSize = 8.sp,
                            maxLines = 1,
                            modifier = Modifier.basicMarquee(
                                1000,  //how many times to repeat
                                animationMode = Immediately,
                                delayMillis = 300
                            )
                        )
                    })

                }
            }
        }


    }
}
