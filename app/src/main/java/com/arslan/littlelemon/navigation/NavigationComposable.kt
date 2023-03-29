package com.arslan.littlelemon.navigation

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arslan.littlelemon.*

@Composable
fun NavigationComposable(
    sharedPreferences: SharedPreferences,
    databaseMenuItems: List<MenuItemRoom>
) {

    val navController = rememberNavController()
    val isLoggedIn = sharedPreferences.getBoolean(MainActivity.IS_LOGGED_IN_KEY, false)

    val startDestination = if (isLoggedIn) {
        Home.route
    } else {
        Onboarding.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Onboarding.route) {
            OnboardingScreen(navController, sharedPreferences)
        }
        composable(Home.route) {
            HomeScreen(navController, databaseMenuItems)
        }
        composable(Profile.route) {
            ProfileScreen(navController, sharedPreferences)
        }
    }

}