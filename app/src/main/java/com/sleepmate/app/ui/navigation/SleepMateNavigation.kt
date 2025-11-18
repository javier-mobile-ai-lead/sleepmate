package com.sleepmate.app.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sleepmate.app.ui.screen.aihelp.AIHelpScreen
import com.sleepmate.app.ui.screen.habits.HabitsScreen
import com.sleepmate.app.ui.screen.home.HomeScreen
import com.sleepmate.app.ui.screen.progress.ProgressScreen
import com.sleepmate.app.ui.screen.settings.SettingsScreen
import com.sleepmate.app.ui.screen.sleeptimer.SleepTimerScreen
import com.sleepmate.app.ui.screen.splash.SplashScreen
import com.sleepmate.app.ui.screen.videorecommendations.VideoRecommendationsScreen

@Composable
fun SleepMateNavigation(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToSleepTimer = {
                        navController.navigate(Screen.SleepTimer.route)
                    },
                    onNavigateToHabits = {
                        navController.navigate(Screen.Habits.route)
                    },
                    onNavigateToVideos = {
                        navController.navigate(Screen.VideoRecommendations.route)
                    },
                    onNavigateToAIHelp = {
                        navController.navigate(Screen.AIHelp.route)
                    },
                    onNavigateToSettings = {
                        navController.navigate(Screen.Settings.route)
                    },
                    onNavigateToProgress = {
                        navController.navigate(Screen.Progress.route)
                    }
                )
            }
            
            composable(Screen.SleepTimer.route) {
                SleepTimerScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
            
            composable(Screen.Habits.route) {
                HabitsScreen(
                    viewModel = hiltViewModel(),
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
            
            composable(Screen.VideoRecommendations.route) {
                VideoRecommendationsScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
            
            composable(Screen.AIHelp.route) {
                AIHelpScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
            
            composable(Screen.Progress.route) {
            ProgressScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
            
            composable(Screen.Settings.route) {
                SettingsScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}