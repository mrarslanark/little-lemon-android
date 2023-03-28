package com.arslan.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.arslan.littlelemon.navigation.NavigationComposable
import com.arslan.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationComposable(sharedPreferences)
                }
            }
        }
    }

    companion object {
        const val IS_LOGGED_IN_KEY = "isLoggedIn"
        const val FIRST_NAME_KEY = "firstName"
        const val LAST_NAME_KEY = "lastName"
        const val LOCATION_KEY = "location"
        const val EMAIL_ADDRESS_KEY = "emailAddress"
    }
}