package com.example.composeuidemo.ui.screens


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeuidemo.ui.screens.myutils.BorderedBox

@Composable
fun RecompositionScreen() {
    // Layout demo 入口列表
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        BorderedBox {
            DerivedStateDemo()

        }
        BorderedBox {
            RememberWithKeyDemo()
        }

    }


}


@Composable
fun DerivedStateDemo() {
    Column {
        // 在点击 counter++ counter-- 按钮时， counter 会变化， condition会被计算，
        // 但是只有当 counter> 5 时， condition 这个state 才变化， 这个 column 才会被重新组合， 下面这句log只有
        // 在 condition 变化时才会被打印， derivedStateOf 说明有效减少了 Column 的重组次数
        // Text("Counter++: $counter") 每次 counter 变化时会被重组

        Log.d("DerivedStateDemo", "DerivedStateDemo start recomposing")

        var counter by remember { mutableIntStateOf(0) }
        val condition by remember { derivedStateOf { counter > 5 } }

        Text("derivedStateOf demo")

        Button(onClick = {
            counter++
        }) {
            Text("Counter++: $counter")
        }

        Button(onClick = {
            counter--
        }) {
            Text("Counter--")
        }


        if (condition) {
            Text("Counter is greater than 5")
        }

    }

}

@Composable
fun RememberWithKeyDemo() {
    Column {
        // 在点击 counter++ counter-- 按钮时， counter 会变化， condition会被计算，
        // 但是只有当 counter> 5 时， condition 这个state 才变化， 这个 column 才会被重新组合， 下面这句log只有
        // 在 condition 变化时才会被打印， derivedStateOf 说明有效减少了 Column 的重组次数
        // Text("Counter++: $counter") 每次 counter 变化时会被重组

        Log.d("RememberWithKeyDemo", "DerivedStateDemo start recomposing")

        var counter: Int by remember { mutableIntStateOf(0) }
//        val condition by remember { derivedStateOf { counter > 5 } }
//        val condition: Boolean = remember(counter) {  counter > 5   }
        val condition by remember(counter) {  mutableStateOf(counter > 5)   }

        Text("RememberWithKeyDemo demo")

        Button(onClick = {
            counter++
        }) {
            Text("Counter++: $counter")
        }

        Button(onClick = {
            counter--
        }) {
            Text("Counter--")
        }


        if (condition) {
            Text("Counter is greater than 5")
        }

    }

}

