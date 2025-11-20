package com.sleepmate.app.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object SleepTimer : Screen("sleep_timer")
    object Habits : Screen("habits")
    object Progress : Screen("progress")
    object VideoRecommendations : Screen("video_recommendations")
    object AIHelp : Screen("ai_help")
    object Settings : Screen("settings")
}