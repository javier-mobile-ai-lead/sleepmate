package com.sleepmate.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sleepmate.app.MainViewModel
import com.sleepmate.app.ui.screen.aihelp.AIHelpScreen
import com.sleepmate.app.ui.screen.habits.HabitsScreen
import com.sleepmate.app.ui.screen.home.HomeScreen
import com.sleepmate.app.ui.screen.onboarding.OnboardingScreen
import com.sleepmate.app.ui.screen.onboarding.SetupProfileScreen
import com.sleepmate.app.ui.screen.onboarding.SetupProfileViewModel
import com.sleepmate.app.ui.screen.progress.ProgressScreen
import com.sleepmate.app.ui.screen.settings.SettingsScreen
import com.sleepmate.app.ui.screen.sleeptimer.SleepTimerScreen
import com.sleepmate.app.ui.screen.splash.SplashScreen
import com.sleepmate.app.ui.screen.videorecommendations.VideoRecommendationsScreen

@Composable
fun SleepMateNavigation(
    navController: NavHostController = rememberNavController(),
    // Accept the destination route from MainActivity
    destinationRoute: String?
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val onboardingCompleted by mainViewModel.isOnboardingCompleted.collectAsState()

    // This effect runs once when the destinationRoute has a value.
    // If the user opens the app normally, it does nothing.
    // If opened from the notification, it navigates to the correct screen.
    LaunchedEffect(destinationRoute) {
        if (destinationRoute == "sleep_timer_screen") {
            navController.navigate(Screen.SleepTimer.route)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    if (onboardingCompleted) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate(Screen.Onboarding.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                }
            )
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(Screen.SetupProfile.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.SetupProfile.route) {
            val setupViewModel: SetupProfileViewModel = hiltViewModel()
            SetupProfileScreen(
                viewModel = setupViewModel,
                onFinish = {
                    navController.navigate(Screen.Home.route) {
                        // Limpia todo el stack para que no pueda volver atrás
                        // y asegura que el estado de Onboarding se considere cerrado
                        popUpTo(Screen.SetupProfile.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

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
                onNavigateBack = { navController.popBackStack() }
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
