package com.example.composeuidemo.ui.screens

import androidx.compose.runtime.Composable
import com.example.composeuidemo.navigation.NavRoute
import com.example.composeuidemo.ui.screens.myutils.CompEntryList

@Composable
fun BasicComponentGroupScreen(onNav: (route: String) -> Unit) {
    // Basic UI components demo 入口列表
    val entryRoutes = listOf(

        NavRoute.TextDemo.route,
        NavRoute.OutlineDemo.route,
    )

    CompEntryList("Basic UI Demo",
        entryRoutes = entryRoutes,
        onEntryClick = { index ->

            onNav(entryRoutes[index])
        }

    )

}