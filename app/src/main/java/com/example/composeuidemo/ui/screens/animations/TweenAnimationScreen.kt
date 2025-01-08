package com.example.composeuidemo.ui.screens.animations

import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.composeuidemo.ui.screens.myutils.BorderedBox

@Composable
fun TweenAnimationScreen() {

    Column {

        TweenCurveDemo()

        Spacer(modifier = Modifier.height(5.dp))

    }

}

@Composable
fun TweenCurveDemo() {
    // 演示4种不同的 tween 曲线，小球移动速度对比
    Column(modifier = Modifier.fillMaxWidth()) {
        // 用于存储父容器的宽度（以 dp 为单位）
        var measuredWidth by remember { mutableStateOf(0f) }

        val density = LocalDensity.current.density

        // 显示测量的宽度（调试用）
        Text("Infinite Animation by animateFloatAsState: ${measuredWidth}dp  $density")

        BorderedBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)

        ) {

            val tweenEasingList = listOf(
                LinearEasing to "LinearEasing",
                LinearOutSlowInEasing to "LinearOutSlowInEasing",
                FastOutSlowInEasing to "FastOutSlowInEasing",
                FastOutLinearInEasing to "FastOutLinearInEasing",
            )

            for (easing in tweenEasingList) {
                val animation: DurationBasedAnimationSpec<Float> = tween(
                    durationMillis = 2000, // 动画持续时间
                    easing = easing.first // 匀速运动
                )

                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                    Text(text = easing.second)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { coordinates ->
                                // 获取父容器的宽度并转换为 dp 单位
                                measuredWidth = coordinates.size.width / density
                            },
                    ) {

                        // 使用无限动画
                        val infiniteTransition = rememberInfiniteTransition()
                        val offset by infiniteTransition.animateFloat(
                            label = "tween",
                            initialValue = 0f,
                            targetValue = measuredWidth - 20, // 动画范围：0 到 (父容器宽度 - 圆形宽度)
                            animationSpec = infiniteRepeatable(
                                animation = animation,
                                repeatMode = RepeatMode.Reverse // 重复模式：从头开始
                            )
                        )
                        // 蓝色圆形
                        Surface(
                            modifier = Modifier
                                .size(20.dp) // 圆形大小
                                .offset(x = offset.dp, y = 0.dp)  // 动态水平偏移
                                .clip(CircleShape),                // 圆形裁剪

                            color = Color.Red

                        ) {
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }

            }
        }
    }
}
