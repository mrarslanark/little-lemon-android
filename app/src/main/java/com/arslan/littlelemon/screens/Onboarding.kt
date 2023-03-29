package com.arslan.littlelemon.screens

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.arslan.littlelemon.MainActivity
import com.arslan.littlelemon.R
import com.arslan.littlelemon.components.BrandButton
import com.arslan.littlelemon.components.Input
import com.arslan.littlelemon.navigation.Destinations
import com.arslan.littlelemon.ui.theme.BrandColors
import com.arslan.littlelemon.ui.theme.BrandTypography

@Composable
fun OnboardingScreen(
    navController: NavHostController,
    sharedPreferences: SharedPreferences
) {

    val firstName = remember { mutableStateOf(TextFieldValue("John")) }
    val firstNameError = remember { mutableStateOf("") }

    val lastName = remember { mutableStateOf(TextFieldValue("Doe")) }
    val lastNameError = remember { mutableStateOf("") }

    val location = remember { mutableStateOf(TextFieldValue("New York, USA")) }

    val emailAddress = remember { mutableStateOf(TextFieldValue("johndoe@email.com")) }
    val emailAddressError = remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF495E57)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to",
                style = BrandTypography.Subtitle,
                color = Color.White,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "Little Lemon",
                style = BrandTypography.DisplayTitle,
                color = BrandColors.PrimaryVariant
            )

            Text(
                text = "Chicago",
                style = BrandTypography.Subtitle,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Registration".uppercase(),
                modifier = Modifier.padding(vertical = 16.dp),
                style = BrandTypography.SectionTitle
            )

            Row {
                Input(
                    label = "First name",
                    onValueChange = { firstName.value = it },
                    inputValue = firstName.value,
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(end = 6.dp),
                    isError = firstNameError.value.isNotEmpty(),
                    errorMessage = firstNameError.value
                )

                Input(
                    label = "Last name",
                    onValueChange = { lastName.value = it },
                    inputValue = lastName.value,
                    modifier = Modifier.weight(0.5f),
                    isError = lastNameError.value.isNotEmpty(),
                    errorMessage = lastNameError.value
                )

            }

            Input(
                label = "Location (Optional)",
                onValueChange = { location.value = it },
                inputValue = location.value,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            Input(
                label = "Email Address",
                keyboardType = KeyboardType.Email,
                onValueChange = { emailAddress.value = it },
                inputValue = emailAddress.value,
                isError = emailAddressError.value.isNotEmpty(),
                errorMessage = emailAddressError.value
            )

        }
        
        BrandButton(
            text = "Register",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {

            val firstNameValue = firstName.value.text
            val lastNameValue = lastName.value.text
            val locationValue = location.value.text
            val emailAddressValue = emailAddress.value.text

            if (firstNameValue.isBlank()) {
                firstNameError.value = "Field Required"
            } else {
                firstNameError.value = ""
            }


            if (lastNameValue.isBlank()) {
                lastNameError.value = "Field Required"
            } else {
                lastNameError.value = ""
            }


            if (emailAddressValue.isBlank()) {
                emailAddressError.value = "Field Required"
            } else {
                emailAddressError.value = ""
            }

            if (firstNameValue.isBlank() || lastNameValue.isBlank() || emailAddressValue.isBlank()) {
                return@BrandButton
            }

            sharedPreferences
                .edit()
                .putString(MainActivity.FIRST_NAME_KEY, firstNameValue)
                .putString(MainActivity.LAST_NAME_KEY, lastNameValue)
                .putString(MainActivity.LOCATION_KEY, locationValue)
                .putString(MainActivity.EMAIL_ADDRESS_KEY, emailAddressValue)
                .putBoolean(MainActivity.IS_LOGGED_IN_KEY, true)
                .apply()

            navController.navigate(route = Destinations.Home.route)
        }
    }
}