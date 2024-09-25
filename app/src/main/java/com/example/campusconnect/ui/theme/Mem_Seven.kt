package com.example.campusconnect.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.campusconnect.R
import androidx.compose.ui.Modifier


@Composable
fun MemSeven() {
    var memberNames by remember { mutableStateOf(List(5) { "" }) }
    var memberImages by remember { mutableStateOf(List(5) { R.drawable.bgm }) } // Use drawable resource ID
    var vacanciesLink by remember { mutableStateOf("") }
    var volunteeringLink by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.bgm),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay content with a semi-transparent background
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White.copy(alpha = 0.8f))
        ) {
            // Members Section
            Text(
                text = "Members",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Current Members Title
            Text(
                text = "Current Members",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // 5 Slots for Profile Pictures and Names
            repeat(5) { index ->
                MemberSlot(
                    name = memberNames[index],
                    onNameChange = { newName ->
                        memberNames = memberNames.toMutableList().apply { this[index] = newName }
                    },
                    onImageChange = { newImageRes ->
                        memberImages = memberImages.toMutableList().apply { this[index] = newImageRes }
                    },
                    imageResId = memberImages[index]
                )
            }

            // Vacancies Section
            Text(
                text = "Vacancies",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            TextField(
                value = vacanciesLink,
                onValueChange = { vacanciesLink = it },
                label = { Text("Vacancies Link") },
                modifier = Modifier.fillMaxWidth()
            )

            // Volunteering Section
            Text(
                text = "Volunteering",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            TextField(
                value = volunteeringLink,
                onValueChange = { volunteeringLink = it },
                label = { Text("Volunteering Link") },
                modifier = Modifier.fillMaxWidth()
            )

            // Save Changes Button
            Button(
                onClick = {
                    // Handle save changes logic here
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Save Changes")
            }
        }
    }
}

@Composable
fun MemberSlot(
    name: String,
    onNameChange: (String) -> Unit,
    onImageChange: (Int) -> Unit,
    imageResId: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Picture
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Name Slot
        TextField(
            value = name,
            onValueChange = onNameChange,
            placeholder = { Text("Member Name") },
            modifier = Modifier.weight(1f)
        )

        // Image Change Button (simulated)
        Button(
            onClick = {
                // Simulate image change
                onImageChange(R.drawable.profile) // Replace with your image selection logic
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text("Change Image")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMemSeven() {
    MemSeven()
}
