package com.arslan.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.arslan.littlelemon.navigation.Profile

@Composable
fun HomeScreen(navController: NavHostController) {
    Column {
        Text(text = "Welcome to Home Screen")
        Button(onClick = {
            navController.navigate(Profile.route)
        }) {
            Text(text = "to Profile Screen")
        }
    }
}