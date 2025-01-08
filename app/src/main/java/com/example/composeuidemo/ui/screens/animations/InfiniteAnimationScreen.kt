package com.example.composeuidemo.ui.screens.animations

import android.util.Log
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeuidemo.R
import com.example.composeuidemo.ui.screens.myutils.BorderedBox

@Composable
fun InfiniteAnimationScreen() {

    Column {
        FanInfinitedByAnimateState()

        Spacer(modifier = Modifier.height(5.dp))

        FansInfiniteTransition()
    }

}

@Composable
fun FanInfinitedByAnimateState() {
    val imageVector = painterResource(id = R.drawable.vector_fan)

    // 定义一个无限循环的动画状态
    // 使用 animateFloatAsState 来创建一个动画值
    var rotateDegree by remember { mutableFloatStateOf(0f) }
    val animatedRotateDegree by animateFloatAsState(
        label = "rotate_degree",
        targetValue = rotateDegree,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    LaunchedEffect(Unit) {
        rotateDegree = (rotateDegree + 360)  // change degree to target value
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        Text("Infinite Animation by animateFloatAsState")
        BorderedBox {

            Image(
                painter = imageVector,
                contentDescription = "Your content description here",
                modifier = Modifier.rotate(animatedRotateDegree.toFloat())
            )
        }
    }
}

@Composable
fun FansInfiniteTransition() {

    val imageVector = painterResource(id = R.drawable.vector_fan)

    val MaxDuration = 5000 // 5 sec
    var speed by remember { mutableFloatStateOf(1f) }

    // slider 控制speed 1-10倍， UI上左边应该最慢，所做个反向
    val speedReversed by remember {   derivedStateOf { (10-speed+1) } }


    // 定义一个无限循环的动画状态
    val infiniteTransition = rememberInfiniteTransition(label = "infinite degree")

    // 使用无限循环的动画状态来控制图像的旋转角度
    // infiniteTransition.animateFloat 接口没办法动态改变 animationSpec 的 duration, 所以没办法动态改变动态周期。
    // 改成通过 调整 animationSpec 的倍速来改变旋转速度
    // 但是，如果旋转角度积累不是 360，会不连续， 会看到抖动
    //
    val rotateDegree by  infiniteTransition.animateFloat(
        label = "degree",
        initialValue = 0f,
        targetValue = 360f*speed,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = MaxDuration,
                easing = LinearEasing
            ), // 动画持续时间为1000毫秒
            repeatMode = RepeatMode.Restart // 动画重复模式为重新开始
        )
    )

    LaunchedEffect(rotateDegree, speed) {
        // 每次 slider speed改变， rotateDegree 都会被重置，确保从 0 度开始
        Log.e("Infinite", "rotateDegree: $rotateDegree  $speed")
    }

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Infinite Animation by infiniteTransition.animateFloat")

        BorderedBox {
            // 使用 AnimatedVisibility 组件来显示图像，并根据动画状态控制其可见性
            Image(
                painter = imageVector,
                contentDescription = "Your content description here",
                modifier = Modifier.rotate(rotateDegree.toFloat() / 1)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Slider(value = speed, onValueChange = { newValue ->
                speed = newValue
                Log.e("Infinite", "speed: $speed")
            }, valueRange = 1f..10f, steps = 8, )
        }
    }
}


