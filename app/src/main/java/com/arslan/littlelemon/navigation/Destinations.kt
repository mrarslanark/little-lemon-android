package com.arslan.littlelemon.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object Onboarding: Destinations(route = "ONBOARDING")

    object Home: Destinations(
        route = "HOME",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile: Destinations(
        route = "PROFILE",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object ItemDetails: Destinations(route = "ITEM_DETAIL")
    object Search: Destinations(route = "SEARCH")
    object EditProfile: Destinations(route = "EDIT_PROFILE")
}