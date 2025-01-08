package com.example.composeuidemo.navigation

sealed class NavRoute(val route: String) {
    object Splash : NavRoute("SplashScreen")
    object Home : NavRoute("home")
    object LayoutGroup : NavRoute("LayoutDemos")
    object AnimationGroup : NavRoute("AnimationDemos")
    object BasicComponentGroup : NavRoute("BasicUIDemos")
    object RecompositionDemos : NavRoute("RecompositionDemos")
    object SideEffectGroup : NavRoute("SideEffectDemos")

    // demo details
    //// basic demos
    object TextDemo : NavRoute("TextDemos")
    object OutlineDemo : NavRoute("OutlineDemos")

    //// layout demos
    object LayoutAlignArrangeDemo : NavRoute("LayoutAlignArrangeDemo")

    //// animation demos
    object AnimatedVisibilityDemo : NavRoute("AnimatedVisibilityDemo")
    object InfiniteAnimationDemo : NavRoute("InfiniteAnimationDemo")
    object TweenAnimationDemo : NavRoute("TweenAnimationDemo")
    object SpringAnimationDemo : NavRoute("SpringAnimationDemo")
    object AnimatedContentDemo : NavRoute("AnimatedContentDemo")

    //// side effect demos
    object LaunchedEffectDemo : NavRoute("DemoLaunchedEffectDemo")
    object UpdatedStateDemo : NavRoute("DemoUpdatedStateDemo")


    //// MVI demos: ViewModel, Intent, VmFactory
    object MVIDemo : NavRoute("MVI Demo")

}