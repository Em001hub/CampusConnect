package com.example.campusconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import com.example.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun Second(navigationToFirst: () -> Unit, navigationToSignUp: () -> Unit, navigationToForgotPassword: () -> Unit) {
    var enteredEmail by remember { mutableStateOf("") }
    var enteredMoodleId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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

        // Main column layout on top of the background image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Heading at the top center
            Text(
                text = "Log In",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            )

            // Text field for email
            OutlinedTextField(
                value = enteredEmail,
                onValueChange = { enteredEmail = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Text field for Moodle ID
            OutlinedTextField(
                value = enteredMoodleId,
                onValueChange = { enteredMoodleId = it },
                label = { Text("Moodle ID") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Text field for password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Submit button with purple color
            Button(
                onClick = {
                    // Handle login logic here
                    navigationToFirst() // Call the navigation function
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
            ) {
                Text(text = "Submit")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // "Forgot Password?" text
            Text(
                text = "Forgot Password?",
                style = MaterialTheme.typography.bodyMedium.copy(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Blue,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable { navigationToForgotPassword() } // Navigate to forgot password
            )

            // "Don't have an account? Sign Up" text
            Text(
                text = "Don't have an account? Sign Up",
                style = MaterialTheme.typography.bodyMedium.copy(
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Blue,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable { navigationToSignUp() } // Navigate to sign up
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSecond() {
    CampusConnectTheme {
        Second(navigationToFirst = {}, navigationToSignUp = {}, navigationToForgotPassword = {})
    }
}
