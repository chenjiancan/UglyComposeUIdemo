package com.example.composeuidemo.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeuidemo.ui.screens.AnimationGroupScreen
import com.example.composeuidemo.ui.screens.BasicComponentGroupScreen
import com.example.composeuidemo.ui.screens.HomeScreen
import com.example.composeuidemo.ui.screens.LayoutGroupScreen
import com.example.composeuidemo.ui.screens.RecompositionScreen
import com.example.composeuidemo.ui.screens.SideEffectGroupScreen
import com.example.composeuidemo.ui.screens.SplashScreen
import com.example.composeuidemo.ui.screens.animations.AnimatedContentScreen
import com.example.composeuidemo.ui.screens.animations.AnimatedVisibilityScreen
import com.example.composeuidemo.ui.screens.animations.InfiniteAnimationScreen
import com.example.composeuidemo.ui.screens.animations.TweenAnimationScreen
import com.example.composeuidemo.ui.screens.basiccomponents.OutlineDemoScreen
import com.example.composeuidemo.ui.screens.basiccomponents.TextDemoScreen
import com.example.composeuidemo.ui.screens.layout.AlignmentArrangementScreen
import com.example.composeuidemo.ui.screens.sideeffects.DemoLaunchedEffectScreen
import com.example.composeuidemo.ui.screens.sideeffects.DemoUpdatedStateScreen


@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        enterTransition = {
            fadeIn(tween(500))
        },
        exitTransition = {
            fadeOut(tween(500))
        },
        startDestination = NavRoute.Splash.route  // First screen to show
    ) {

        composable(NavRoute.Splash.route)
        {
            SplashScreen(navToHome =  {
                navController.navigate(NavRoute.Home.route) {
                    popUpTo(NavRoute.Splash.route) {
                        inclusive = true
                    } // Remove SplashScreen from back stack
                }
            })
        }

        composable(NavRoute.Home.route)
        {
            HomeScreen(onNav = { route: String ->
                navController.navigate(route)
            })
        }

        // lists
        composable(NavRoute.BasicComponentGroup.route)
        {
            BasicComponentGroupScreen(onNav = { route: String ->
                try {
                    navController.navigate(route)
                } catch (e: Exception) {
                    println(e)
                }
            })
        }

        composable(NavRoute.LayoutGroup.route)
        {
            LayoutGroupScreen(onNav = { route: String ->
                navController.navigate(route)
            })
        }

        composable(NavRoute.AnimationGroup.route)
        {
            AnimationGroupScreen(onNav = { route: String ->
                navController.navigate(route)
            })
        }

        composable(NavRoute.SideEffectGroup.route)
        {
            SideEffectGroupScreen(onNav = { route: String ->
                navController.navigate(route)
            })
        }

        // demos
        //// basic demos
        composable(NavRoute.TextDemo.route)
        {
            TextDemoScreen()
        }
        composable(NavRoute.OutlineDemo.route)
        {
            OutlineDemoScreen()
        }

        //// layout demos
        composable(NavRoute.LayoutAlignArrangeDemo.route)
        {
            AlignmentArrangementScreen()
        }

        //// recomposition demos
        composable(NavRoute.RecompositionDemos.route)
        {
            RecompositionScreen()
        }

        //// animation demos
        composable(NavRoute.AnimatedVisibilityDemo.route)
        {
            AnimatedVisibilityScreen()
        }

        composable(NavRoute.InfiniteAnimationDemo.route)
        {
            InfiniteAnimationScreen()
        }

        composable(NavRoute.TweenAnimationDemo.route)
        {
            TweenAnimationScreen()
        }

        composable(NavRoute.AnimatedContentDemo.route)
        {
            AnimatedContentScreen()
        }


        //// side effect demos
        composable(NavRoute.LaunchedEffectDemo.route)
        {
            DemoLaunchedEffectScreen()
        }
        composable(NavRoute.UpdatedStateDemo.route)
        {
            DemoUpdatedStateScreen()
        }


        //// MVIDemo
        composable(NavRoute.MVIDemo.route)
        {
        }

    }

}
