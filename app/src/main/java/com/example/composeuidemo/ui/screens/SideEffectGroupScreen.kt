package com.example.composeuidemo.ui.screens


import androidx.compose.runtime.Composable
import com.example.composeuidemo.navigation.NavRoute
import com.example.composeuidemo.ui.screens.myutils.CompEntryList


@Composable
fun SideEffectGroupScreen(onNav: (route: String) -> Unit) {

    // SideEffect demos 入口列表
    val entryRoutes = listOf(
        NavRoute.LaunchedEffectDemo.route,
        NavRoute.UpdatedStateDemo.route,
    )
    CompEntryList("Side Effect Demo", entryRoutes, onEntryClick = { index ->
        onNav(entryRoutes[index])
    })

}

