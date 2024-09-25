package com.example.campusconnect.ui.theme




import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

// Define the Session data class with correct parameters
data class Session(
    val id: String,
    val name: String,
    val date: String,
    val duration: String,
    val room: String,
    val description: String,
    var totalTickets: Int = 100,
    var ticketsSold: Int = 0
) {
    val ticketsLeft: Int
        get() = totalTickets - ticketsSold
}

@Composable
fun SessionRegistrationPage() {
    // Create an instance of Session
    val session = remember {
        Session(
            id = "1",  // Provide a value for id
            name = "Tech Session",
            date = "2024-09-30",
            duration = "2 hours",
            room = "Room 101",
            description = "Join us for an engaging Tech Session where you will learn about the latest advancements in technology. This session is perfect for beginners and experienced tech enthusiasts alike. You'll have the opportunity to participate in hands-on activities, ask questions, and network with industry professionals. Don't miss out on this chance to enhance your skills and expand your knowledge."
        )
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Session Ticket Registration", style = MaterialTheme.typography.headlineLarge)
        SessionCard(session) {
            // Register for tickets if available
            if (session.ticketsLeft > 0) {
                session.ticketsSold += 1 // Simulate ticket sale
            }
        }
    }
}

@Composable
fun SessionCard(session: Session, onRegister: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(session.name, style = MaterialTheme.typography.titleMedium)
            Text("Date: ${session.date}", style = MaterialTheme.typography.bodyMedium)
            Text("Duration: ${session.duration}", style = MaterialTheme.typography.bodyMedium)
            Text("Room: ${session.room}", style = MaterialTheme.typography.bodyMedium)
            Text("Description: ${session.description}", style = MaterialTheme.typography.bodyMedium)
            Text("Tickets Left: ${session.ticketsLeft}", style = MaterialTheme.typography.bodyMedium)

            if (session.ticketsLeft > 0) {
                Button(onClick = onRegister) {
                    Text("Register for Session")
                }
            } else {
                Text("Tickets Sold Out", color = Color.Red)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSessionRegistrationPage() {
    SessionRegistrationPage()
}
