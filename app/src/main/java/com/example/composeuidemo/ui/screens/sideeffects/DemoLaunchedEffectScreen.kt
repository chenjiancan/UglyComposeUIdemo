package com.example.composeuidemo.ui.screens.sideeffects

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeuidemo.ui.screens.myutils.BorderedBox

@Composable
fun DemoLaunchedEffectScreen() {
    // 演示  LaunchedEffect  使用场景
    // Layout demo 入口列表
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        BorderedBox{
            SideEffectTimingDemo()
        }
    }
}


@Composable
fun SideEffectTimingDemo() {
    var counter by remember { mutableIntStateOf(0) }
    Log.e("SideEffectTimingDemo", "SideEffectTimingDemo start recomposing")
    LaunchedEffect(key1 = Unit) {
        // 只会在最开始执行一次, 因为 key1 永远等于 Unit, 适合初始化
        Log.e("SideEffectTimingDemo", "LaunchedEffect(Unit) start")
    }

    LaunchedEffect(key1 = counter) {
        // 最开始执行一次, 每次 counter 变化都会执行一次, 因为 key1 会变化，适合根据数据变化执行逻辑
        Log.e("SideEffectTimingDemo", "LaunchedEffect(counter) start")
    }

    SideEffect {
        // 每次点击 Button 都会执行 SideEffect, 因为这层 composable 会被重组
        Log.e("SideEffectTimingDemo", "SideEffect start")

    }

    DisposableEffect(Unit) {
        // 只会在最开始执行一次, 适合初始化
        Log.e("SideEffectTimingDemo", "DisposableEffect(Unit) start ${Thread.currentThread()}")

        onDispose {
            // 只会在最开始执行一次, 适合清理, 且必须要有 onDispose 回调
            Log.e("SideEffectTimingDemo", "DisposableEffect(Unit) onDispose")
        }
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text("$counter")

        LaunchedEffect(Unit) {
            // 只会在最开始执行一次
            Log.e("SideEffectTimingDemo", "LaunchedEffect(Unit) column start ${Thread.currentThread()}")
        }

        Button(onClick = {
            counter++
        }) {
            Log.e("SideEffectTimingDemo", "LaunchedEffect(Unit) Button composable start ${Thread.currentThread()}")
            Text("Counter++")

        }

    }
}
