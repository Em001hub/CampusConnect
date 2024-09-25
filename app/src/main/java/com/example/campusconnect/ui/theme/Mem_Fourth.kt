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
fun MemFourth(navigationToFirst: () -> Unit) {

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
            // Top section with profile picture, greeting, and logout button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Profile section
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.profile), // Replace with actual profile image resource
                        contentDescription = "Profile Picture",
                        modifier = Modifier.size(40.dp).padding(end = 8.dp)
                    )
                    Column {
                        Text("Hello, User Name", style = MaterialTheme.typography.bodyLarge)
                    }
                }
                // Logout button
                Button(
                    onClick = { /* Handle Logout */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                ) {
                    Text("Logout")
                }
            }

            // Center section with Help, FAQ, and main buttons
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
                        onClick = { /* Handle Help */ },
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                    ) {
                        Text("Help")
                    }
                    Button(
                        onClick = { /* Handle FAQ */ },
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
                    ) {
                        Text("FAQ")
                    }
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
fun PreviewMemFourth() {
    CampusConnectTheme {
        MemFourth(navigationToFirst = { /* No-op */ })
    }
}
