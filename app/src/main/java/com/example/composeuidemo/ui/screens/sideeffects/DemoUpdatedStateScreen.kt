package com.example.composeuidemo.ui.screens.sideeffects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeuidemo.ui.screens.myutils.BorderedBox

@Composable
fun DemoUpdatedStateScreen() {
    // 演示 RememberUpdatedState 使用场景
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        BorderedBox {

            OuterComposable()
        }
    }
}


@Composable
fun InnerComposable(counterParam: Int) {
    val innerCounter by remember { mutableIntStateOf(counterParam) }
    val innerCounterUpdated by rememberUpdatedState ( counterParam )
    Column {
        Text("counterParam: $counterParam")   // 会变化，因为外部的 counter 变化都会触发关心它的 composable 重新组合
        Text("innerCounter: $innerCounter")   // 不会变化， 因为 remember 只会在第一次执行， 后面不会执行
        Text("innerCounterUpdated: $innerCounterUpdated") // 会变化， 因为 rememberUpdatedState 会感知数据变化
    }
}

@Composable
fun OuterComposable() {
    var counter by remember { mutableIntStateOf(0) }
    Button(onClick = {
        counter++
    }) {
        Text("Click to increase: $counter")
    }
    InnerComposable(counter)
}
