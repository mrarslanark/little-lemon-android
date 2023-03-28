package com.arslan.littlelemon

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arslan.littlelemon.components.BrandButton
import com.arslan.littlelemon.components.Input
import com.arslan.littlelemon.ui.theme.BrandColors
import com.arslan.littlelemon.ui.theme.BrandTypography

@Composable
fun OnboardingScreen() {

    val firstName = remember { mutableStateOf(TextFieldValue("")) }
    val firstNameError = remember { mutableStateOf("") }

    val lastName = remember { mutableStateOf(TextFieldValue("")) }
    val lastNameError = remember { mutableStateOf("") }

    val location = remember { mutableStateOf(TextFieldValue("")) }

    val emailAddress = remember { mutableStateOf(TextFieldValue("")) }
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
                .weight(0.8f, true),
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
        
        BrandButton(text = "Register") {
            if (firstName.value.text.isEmpty()) {
                firstNameError.value = "Field Required"
            } else {
                firstNameError.value = ""
            }


            if (lastName.value.text.isEmpty()) {
                lastNameError.value = "Field Required"
            } else {
                lastNameError.value = ""
            }


            if (emailAddress.value.text.isEmpty()) {
                emailAddressError.value = "Field Required"
            } else {
                emailAddressError.value = ""
            }

            if (firstName.value.text.isEmpty() || lastName.value.text.isEmpty() || emailAddress.value.text.isEmpty()) {
                return@BrandButton
            }
        }
    }
}