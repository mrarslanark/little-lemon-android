package com.arslan.littlelemon

import android.widget.Button
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.arslan.littlelemon.navigation.Profile
import com.arslan.littlelemon.ui.theme.BrandColors
import com.arslan.littlelemon.ui.theme.BrandTypography
import com.arslan.littlelemon.ui.theme.MarkaziTextFamily
import java.util.*

@Composable
fun HomeScreen(navController: NavHostController) {

    val selectedCategory: MutableState<Categories?> = remember {
        mutableStateOf(null)
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
           Image(
               painter = painterResource(id = R.drawable.logo),
               contentDescription = "Logo",
               Modifier.aspectRatio(5f, true)
           )
           IconButton(onClick = {
               navController.navigate(Profile.route)
           }) {
               Image(imageVector = Icons.Default.Person, contentDescription = "Profile Icon")
           }
        }

        Column(
            modifier = Modifier
                .background(BrandColors.Primary)
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Text(
                text = "Little Lemon",
                style = BrandTypography.DisplayTitle,
                color = BrandColors.PrimaryVariant,
            )
            Row {
                Column(
                    modifier = Modifier.weight(0.6f, true),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Chicago",
                        style = BrandTypography.Subtitle,
                        color = Color.White,
                        modifier = Modifier.absoluteOffset(y = (-26).dp)
                    )

                    Text(
                        text = stringResource(id = R.string.introduction),
                        style = BrandTypography.ParagraphText,
                        color = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Image",
                    modifier = Modifier
                        .height(130.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
            IconButton(
                onClick = {
                    // TODO: Go to Search Screen
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Image(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .background(MaterialTheme.colors.background)
                        .padding(8.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Text(
                text = "Order for Delivery".uppercase(),
                style = BrandTypography.SectionTitle
            )
            LazyRow(
                modifier = Modifier.padding(top = 12.dp)
            ) {
                items(Categories.values()) { category ->
                    var selectedBackground = BrandColors.SurfaceColor
                    var selectedTextColor = Color.Black
                    if (selectedCategory.value == category) {
                        selectedBackground = Color(0xFF333333)
                        selectedTextColor = Color.White
                    }
                    Button(
                        onClick = {
                            selectedCategory.value = if (selectedCategory.value == category) {
                                null
                            } else {
                                category
                            }
                        },
                        modifier = Modifier
                            .padding(end = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = selectedBackground,
                        ),
                        shape = RoundedCornerShape(100),
                        elevation = null,
                    ) {
                        Text(
                            text = category.name,
                            style = BrandTypography.SectionCategories,
                            color = selectedTextColor
                        )
                    }
                }
            }
        }

        LazyColumn {
            items(count = 10) { index ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .weight(0.8f, true)
                            .height(90.dp)
                            .padding(end = 12.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Column {
                            Text(
                                text = "Greek Salad",
                                style = BrandTypography.CardTitle
                            )
                            Text(
                                text = "The famous greek salad of crispy lettuce, peppers, olives, and our Chic...",
                                style = BrandTypography.ParagraphText,
                                maxLines = 2,
                            )
                        }
                        Text(
                            text = "$12.99",
                            style = BrandTypography.HighlightText
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.FillWidth,
                    )
                }
                if (index < 10) {
                    Divider(
                        color = BrandColors.SurfaceColor, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }

    }
}