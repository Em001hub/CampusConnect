package com.example.campusconnect.ui.theme


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

data class Workshop(
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
fun WorkshopRegistrationPage() {
    var showDialog by remember { mutableStateOf(false) }
    val workshops = remember { mutableStateListOf<Workshop>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Workshop Ticket Registration", style = MaterialTheme.typography.headlineLarge)

        Button(onClick = { showDialog = true }) {
            Text("Create Workshop")
        }

        workshops.forEach { workshop ->
            WorkshopCard(workshop) {
                if (workshop.ticketsLeft > 0) {
                    workshop.ticketsSold += 1 // Simulate ticket sale
                }
            }
        }
    }

    if (showDialog) {
        WorkshopDialog(onDismiss = { showDialog = false }) { newWorkshop ->
            workshops.add(newWorkshop)
            showDialog = false
        }
    }
}

@Composable
fun WorkshopCard(workshop: Workshop, onRegister: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(workshop.name, style = MaterialTheme.typography.titleMedium)
            Text("Date: ${workshop.date}", style = MaterialTheme.typography.bodyMedium)
            Text("Duration: ${workshop.duration}", style = MaterialTheme.typography.bodyMedium)
            Text("Room: ${workshop.room}", style = MaterialTheme.typography.bodyMedium)
            Text("Description: ${workshop.description}", style = MaterialTheme.typography.bodyMedium)
            Text("Tickets Left: ${workshop.ticketsLeft}", style = MaterialTheme.typography.bodyMedium)

            if (workshop.ticketsLeft > 0) {
                Button(onClick = onRegister) {
                    Text("Register for Event")
                }
            } else {
                Text("Tickets Sold Out", color = Color.Red)
            }
        }
    }
}

@Composable
fun WorkshopDialog(onDismiss: () -> Unit, onCreate: (Workshop) -> Unit) {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var room by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Create Workshop") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Workshop Name") },
                    isError = errorMessage.isNotEmpty()
                )
                TextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Date (YYYY-MM-DD)") },
                    isError = errorMessage.isNotEmpty()
                )
                TextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("Duration") },
                    isError = errorMessage.isNotEmpty()
                )
                TextField(
                    value = room,
                    onValueChange = { room = it },
                    label = { Text("Room") },
                    isError = errorMessage.isNotEmpty()
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    isError = errorMessage.isNotEmpty()
                )
                if (errorMessage.isNotEmpty()) {
                    Text(errorMessage, color = MaterialTheme.colorScheme.error)
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (name.isBlank() || date.isBlank() || duration.isBlank() || room.isBlank() || description.isBlank()) {
                    errorMessage = "All fields are required."
                } else {
                    onCreate(Workshop(
                        id = "W${System.currentTimeMillis()}",
                        name = name,
                        date = date,
                        duration = duration,
                        room = room,
                        description = description
                    ))
                    onDismiss()
                }
            }) {
                Text("Create")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewWorkshopRegistrationPage() {
    WorkshopRegistrationPage()
}
