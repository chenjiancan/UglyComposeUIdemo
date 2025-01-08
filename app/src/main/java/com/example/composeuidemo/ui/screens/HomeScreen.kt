package com.example.composeuidemo.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.composeuidemo.navigation.NavRoute
import com.example.composeuidemo.ui.screens.myutils.CompEntryList
import java.io.File
import java.nio.file.Paths

@Composable
fun HomeScreen(onNav: (route: String) -> Unit) {
    // 首页， 所有二级页面的入口列表
    val entryRoutes = listOf(
        NavRoute.BasicComponentGroup.route,
        NavRoute.LayoutGroup.route,
        NavRoute.RecompositionDemos.route,
        NavRoute.AnimationGroup.route,
        NavRoute.SideEffectGroup.route,
    )
    CompEntryList("Compose UI Demo", entryRoutes, onEntryClick = { index ->
        onNav(entryRoutes[index])
    })



}