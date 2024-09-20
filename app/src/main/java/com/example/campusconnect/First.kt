package com.example.campusconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun First(navigationToSecond: () -> Unit, navigationToThird: () -> Unit, navigationToFourth: () -> Unit) {
    // Load the background image
    val backgroundImage: Painter = painterResource(id = R.drawable.bgm) // Replace with your image resource ID

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize() // Scale the image to cover the entire background
                .align(Alignment.Center)
        )

        // Main content on top of the background image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Text at the top
            Text(
                text = "Welcome to CampusConnect",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Column to align buttons vertically
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp), // Space between buttons
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navigationToThird() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                ) {
                    Text(text = "Sign Up")
                }
                Button(
                    onClick = { navigationToSecond() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                ) {
                    Text(text = "Log In")
                }
                Button(
                    onClick = { navigationToFourth() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                ) {
                    Text(text = "Guest")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFirst() {
    CampusConnectTheme {
        First(
            navigationToSecond = {},
            navigationToThird = {},
            navigationToFourth = {}
        )
    }
}
