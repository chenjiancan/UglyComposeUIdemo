package com.example.composeuidemo.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeuidemo.navigation.NavRoute
import com.example.composeuidemo.ui.screens.myutils.CompEntryList

@Composable
fun LayoutGroupScreen(onNav: (route: String) -> Unit) {
    // Layout demo 入口列表
    val entryRoutes = listOf(

        NavRoute.LayoutAlignArrangeDemo.route,
    )

    CompEntryList("Layout Demo",
        entryRoutes = entryRoutes,
        onEntryClick = { index ->

            onNav(entryRoutes[index])
        }

    )

}