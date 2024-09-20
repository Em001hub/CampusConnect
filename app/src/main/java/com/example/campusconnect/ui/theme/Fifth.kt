package com.example.campusconnect.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.campusconnect.R


@Composable
fun Fifth(navController: NavController) {
    // Load the background image
    val backgroundImage: Painter = painterResource(id = R.drawable.bgm) // Replace with your image resource ID

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Clubs",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Row for existing buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // GDSC Button
                Button(
                    onClick = { navController.navigate("sixth") },
                    modifier = Modifier
                        .weight(1f) // Makes the button fill available space
                        .padding(end = 8.dp), // Adds space between buttons
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A)) // Purple color
                ) {
                    Text("GDSC")
                }

                // DevOps Button
                Button(
                    onClick = { /* Handle DevOps Button Click */ },
                    modifier = Modifier
                        .weight(1f) // Makes the button fill available space
                        .padding(start = 8.dp), // Adds space between buttons
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A)) // Purple color
                ) {
                    Text("DevOps")
                }
            }

            // Spacer between rows
            Spacer(modifier = Modifier.height(16.dp))

            // Row for new buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Antarang Button
                Button(
                    onClick = { /* Handle Antarang Button Click */ },
                    modifier = Modifier
                        .weight(1f) // Makes the button fill available space
                        .padding(end = 8.dp), // Adds space between buttons
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A)) // Purple color
                ) {
                    Text("Antarang")
                }

                // Cybersec Button
                Button(
                    onClick = { /* Handle Cybersec Button Click */ },
                    modifier = Modifier
                        .weight(1f) // Makes the button fill available space
                        .padding(start = 8.dp), // Adds space between buttons
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A)) // Purple color
                ) {
                    Text("Cybersec")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFifth() {
    CampusConnectTheme {
        Fifth(navController = rememberNavController())
    }
}
