package com.example.campusconnect.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.campusconnect.R



@Composable
fun Fifth(navController: NavController, userId: String, onLogout: () -> Unit) {
    // Load the background image
    val backgroundImage: Painter = painterResource(id = R.drawable.bgm) // Replace with your image resource ID
    val userProfilePic: Painter = painterResource(id = R.drawable.profile) // Replace with your user profile image resource ID
    val clubProfilePic: Painter = painterResource(id = R.drawable.profile) // Placeholder for club images

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )

        // Main content on top of the background image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // User Profile Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User Profile Picture
                Image(
                    painter = userProfilePic,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                        .clip(CircleShape) // Make the profile picture circular
                )
                // Greeting Text
                Text(
                    text = "Hello, $userId",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                // Logout Button
                Button(
                    onClick = onLogout,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Logout")
                }
            }

            // Title
            Text(
                text = "Clubs",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            // First Row of Buttons and Images
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // GDSC Button
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = clubProfilePic,
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Button(
                        onClick = { navController.navigate("sixth") },
                        modifier = Modifier
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A))
                    ) {
                        Text("GDSC")
                    }
                }

                // DevOps Button
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = clubProfilePic,
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Button(
                        onClick = { /* Handle DevOps Button Click */ },
                        modifier = Modifier
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A))
                    ) {
                        Text("DevOps")
                    }
                }
            }

            // Spacer between rows
            Spacer(modifier = Modifier.height(16.dp))

            // Second Row of Buttons and Images
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Antarang Button
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = clubProfilePic,
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Button(
                        onClick = { /* Handle Antarang Button Click */ },
                        modifier = Modifier
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A))
                    ) {
                        Text("Antarang")
                    }
                }

                // Cybersec Button
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = clubProfilePic,
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Button(
                        onClick = { /* Handle Cybersec Button Click */ },
                        modifier = Modifier
                            .padding(top = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A))
                    ) {
                        Text("Cybersec")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFifth() {
    CampusConnectTheme {
        Fifth(navController = rememberNavController(), userId = "User123", onLogout = {})
    }
}
