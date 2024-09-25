package com.example.campusconnect.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campusconnect.R

@Composable
fun Seven() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.bgm), // Replace with your background image
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay content with a semi-transparent background
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White.copy(alpha = 0.8f)) // Semi-transparent white background
        ) {
            // Members Section
            Text(
                text = "Members",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Current Member Title
            Text(
                text = "Current Member",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // 5 Slots for Profile Pictures and Names
            repeat(5) {
                MemberSlot()
            }

            // Current Faculty Section
            Text(
                text = "Current Faculty",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // 2 Slots for Faculty
            repeat(2) {
                MemberSlot()
            }

            // Vacancies Section
            Text(
                text = "Vacancies",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Google Form Link
            Text(
                text = "Apply here: [Google Form Link]",
                fontSize = 16.sp,
                color = Color.Blue, // Make it stand out as a link
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Volunteering Section
            Text(
                text = "Volunteering",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // No Volunteers Message
            Text(
                text = "No volunteers required yet, check back soon!",
                fontSize = 16.sp,
                color = Color.Gray // Use a different color to indicate status
            )
        }
    }
}

@Composable
fun MemberSlot() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Picture (placeholder image)
        Image(
            painter = painterResource(id = R.drawable.bgm), // Ensure this resource exists
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Name Slot (you can replace with actual data)
        Text(
            text = "Member Name",
            fontSize = 16.sp,
            color = Color.Black // Explicitly set the color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSeven() {
    Seven()
}
