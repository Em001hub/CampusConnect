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
import com.example.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun Fourth(navigationToFirst: () -> Unit) {

    val backgroundImage: Painter = painterResource(id = R.drawable.bgm)

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
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top section with Help, FAQ, Sign In, Sign Up buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { /* Handle Help */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                ) {
                    Text("Help")
                }
                Button(
                    onClick = { /* Handle FAQ */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                ) {
                    Text("FAQ")
                }
                Button(
                    onClick = { /* Handle Sign In */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                ) {
                    Text("Sign In")
                }
                Button(
                    onClick = { /* Handle Sign Up */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                ) {
                    Text("Sign Up")
                }
            }

            // Center section with Head Council and Clubs buttons
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp), // Adds space above the center section
                contentAlignment = Alignment.Center // Center the content in the box
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center // Center content vertically
                ) {
                    Button(
                        onClick = { /* Handle Head Council */ },
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                    ) {
                        Text("Head Council")
                    }
                    Button(
                        onClick = {
                            navigationToFirst() // Call the navigation function
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                    ) {
                        Text("Clubs")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFourth() {
    CampusConnectTheme {
        Fourth(navigationToFirst = { /* No-op */ })
    }
}
