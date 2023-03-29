package com.arslan.littlelemon.navigation

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arslan.littlelemon.*

@Composable
fun RootNavigationGraph(
    sharedPreferences: SharedPreferences,
    menuItems: List<MenuItemRoom>,
    navController: NavHostController
) {

    val isLoggedIn = sharedPreferences.getBoolean(MainActivity.IS_LOGGED_IN_KEY, false)
    val startRoute = if (isLoggedIn) {
        Destinations.Home.route
    } else {
        Destinations.Onboarding.route
    }

    Scaffold(bottomBar = { BottomNavigator(navController = navController) }) {
        Box(Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = startRoute
            ) {
                composable(route = Destinations.Home.route) {
                    HomeScreen(navController = navController, data = menuItems)
                }
                composable(route = Destinations.Profile.route) {
                    ProfileScreen(navController = navController, sharedPreferences = sharedPreferences)
                }
                composable(route = Destinations.ItemDetails.route) {
                    ItemDetailsScreen()
                }
                composable(route = Destinations.Search.route) {
                    SearchScreen(navController = navController, menuItems = menuItems)
                }
                composable(route = Destinations.EditProfile.route) {
                    EditProfileScreen()
                }
                composable(route = Destinations.Onboarding.route) {
                    OnboardingScreen(navController = navController, sharedPreferences = sharedPreferences)
                }
            }
        }
    }
}


