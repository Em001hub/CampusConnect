package com.example.campusconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.example.campusconnect.ui.theme.CampusConnectTheme

@Composable
fun Third(onSubmit: (String, String, String) -> Unit, onLoginClick: () -> Unit) {
    var moodleId by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.7f)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Sign Up Header
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Error message
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Form Fields
            OutlinedTextField(
                value = moodleId,
                onValueChange = { moodleId = it },
                label = { Text("Moodle ID") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Validate inputs
                    if (moodleId.isEmpty() || email.isEmpty() || fullName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                        errorMessage = "All fields are required."
                    } else if (password != confirmPassword) {
                        errorMessage = "Passwords do not match."
                    } else {
                        errorMessage = ""
                        onSubmit(email, moodleId, password) // Submit data
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color
            ) {
                Text(text = "Submit")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // "Already have an account?" text
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Blue,
                modifier = Modifier
                    .clickable(onClick = onLoginClick)
                    .padding(vertical = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewThird() {
    CampusConnectTheme {
        Third(onSubmit = { _, _, _ -> }, onLoginClick = {})
    }
}
