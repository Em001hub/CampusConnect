package com.example.campusconnect.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.draw.clip // Import for clip
import com.example.campusconnect.R

@SuppressLint("MutableCollectionMutableState")
@Composable
fun MemberPage(userData: UserData) {
    val backgroundImage: Painter = painterResource(id = R.drawable.bgm) // Background image

    var description by remember { mutableStateOf(userData.description) }
    val achievements = remember { mutableStateListOf(*userData.achievements.toTypedArray()) }
    var newAchievement by remember { mutableStateOf(TextFieldValue("")) }
    val imageResId = userData.imageResId

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().align(Alignment.Center)
        )

        // Main content
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Button Row
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { /* Handle Events Click */ }) { Text("Events") }
                Button(onClick = { /* Handle Posts Click */ }) { Text("Posts") }
                Button(onClick = { /* Handle Tickets Click */ }) { Text("Tickets") }
            }

            // Circular Image for Profile
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
                    .padding(8.dp)
            )

            // Description Textbox
            BasicTextField(
                value = description,
                onValueChange = { newValue -> description = newValue },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 16.dp)
                    .padding(8.dp),
                enabled = true
            )

            // Achievement Input
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = newAchievement,
                    onValueChange = { newValue -> newAchievement = newValue },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .border(1.dp, Color.Gray)
                        .padding(8.dp),
                    enabled = true
                )
                Button(onClick = {
                    if (newAchievement.text.isNotBlank()) {
                        achievements.add(newAchievement.text)
                        newAchievement = TextFieldValue("") // Clear the input
                    }
                }) {
                    Text("Add Achievement")
                }
            }

            // Achievements Table
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Achievements",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.Gray
                )
                achievements.forEach { achievement ->
                    AchievementItem(year = "2023", achievement = achievement)
                }
            }

            // Members Button
            Button(onClick = { /* Handle Members Click */ }) {
                Text("Members")
            }
        }
    }
}

@Composable
fun AchievementItem(year: String, achievement: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = year, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
            Text(text = achievement, style = MaterialTheme.typography.bodyMedium, color = Color.Black)
        }
    }
}

data class UserData(
    val description: String,
    val achievements: List<String>,
    val imageResId: Int
)

@Composable
@Preview(showBackground = true)
fun PreviewMemberPage() {
    val dummyUser = UserData(
        description = "This is a sample user description.",
        achievements = listOf("Achievement 1", "Achievement 2"),
        imageResId = R.drawable.profile // Replace with a valid drawable
    )
    MemberPage(userData = dummyUser)
}
