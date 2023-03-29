package com.arslan.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.arslan.littlelemon.navigation.NavigationComposable
import com.arslan.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database"
        ).build()
    }

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
                    val databaseMenuItems = database
                        .menuItemDao()
                        .getAllMenuItems()
                        .observeAsState(emptyList())
                    val menuItemSorted = databaseMenuItems.value.sortedBy { it.title }
                    NavigationComposable(sharedPreferences, menuItemSorted)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menuItems = fetchMenu()
                saveMenuToDatabase(menuItems)
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuItem> {
        val url = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
        val response: MenuData = httpClient.get(url).body()
        response.menu.forEach { Log.d("MainActivity", it.title) }
        return response.menu
    }

    private fun saveMenuToDatabase(menuItems: List<MenuItem>) {
        val menuItemsRoom = menuItems.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }

    companion object {
        const val IS_LOGGED_IN_KEY = "isLoggedIn"
        const val FIRST_NAME_KEY = "firstName"
        const val LAST_NAME_KEY = "lastName"
        const val LOCATION_KEY = "location"
        const val EMAIL_ADDRESS_KEY = "emailAddress"
    }
}