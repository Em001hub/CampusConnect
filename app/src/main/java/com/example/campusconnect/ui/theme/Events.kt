package com.example.campusconnect.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.util.*

data class Event(
    val name: String,
    val duration: String,
    val venue: String,
    val date: String // Date formatted as a String
)

@Composable
fun EventInputForm() {
    var eventName by remember { mutableStateOf(TextFieldValue("")) }
    var eventDuration by remember { mutableStateOf(TextFieldValue("")) }
    var eventVenue by remember { mutableStateOf(TextFieldValue("")) }
    var eventDate by remember { mutableStateOf(TextFieldValue(getCurrentDate())) }
    val events = remember { mutableStateListOf<Event>() }

    // State for selected month
    var selectedMonth by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add Event", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = eventName,
            onValueChange = { eventName = it },
            label = { Text("Event Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = eventDuration,
            onValueChange = { eventDuration = it },
            label = { Text("Duration") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = eventVenue,
            onValueChange = { eventVenue = it },
            label = { Text("Venue") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = eventDate,
            onValueChange = { eventDate = it },
            label = { Text("Date (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (eventName.text.isNotBlank() && eventDuration.text.isNotBlank() &&
                    eventVenue.text.isNotBlank() && eventDate.text.isNotBlank()
                ) {
                    events.add(Event(eventName.text, eventDuration.text, eventVenue.text, eventDate.text))
                    eventName = TextFieldValue("")
                    eventDuration = TextFieldValue("")
                    eventVenue = TextFieldValue("")
                    eventDate = TextFieldValue(getCurrentDate()) // Reset to current date
                }
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Add Event")
        }

        Text("Events:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        for (event in events) {
            EventCard(event)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Month selection using the new function
        MonthDropdownMenu(selectedMonth) { month ->
            selectedMonth = month
        }

        // Display selected month
        MonthView(selectedMonth, events)
    }
}

@Composable
fun MonthDropdownMenu(selectedMonth: Int, onMonthSelected: (Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val monthNames = listOf("January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December")

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(monthNames[selectedMonth])
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            monthNames.forEachIndexed { index, month ->
                DropdownMenuItem(
                    text = { Text(month) },
                    onClick = {
                        onMonthSelected(index)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun MonthView(month: Int, events: List<Event>) {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.DAY_OF_MONTH, 1)

    val monthName = SimpleDateFormat("MMMM", Locale.getDefault()).format(calendar.time)
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)

    Column(modifier = Modifier.padding(vertical = 16.dp)) {
        Text(monthName, fontSize = 20.sp, fontWeight = FontWeight.Bold)

        // Weekday headers
        Row(modifier = Modifier.fillMaxWidth()) {
            val weekdays = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
            for (day in weekdays) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .border(1.dp, Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(day)
                }
            }
        }

        var dayCounter = 1
        for (week in 0 until 6) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (day in 1..7) {
                    val dayToShow = if (week == 0 && day < firstDayOfMonth) {
                        ""
                    } else if (dayCounter > daysInMonth) {
                        ""
                    } else {
                        dayCounter.toString().also { dayCounter++ }
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .background(
                                if (dayToShow.isNotEmpty() && events.any { event ->
                                        event.date == String.format(Locale.getDefault(), "%04d-%02d-%02d", calendar.get(Calendar.YEAR), month + 1, dayToShow.toInt())
                                    }) Color(0xFFADD8E6) // Light Blue
                                else Color.Transparent
                            )
                            .border(1.dp, Color.Gray), // Add border for each day
                        contentAlignment = Alignment.Center
                    ) {
                        if (dayToShow.isNotEmpty()) {
                            Text(dayToShow)
                        }
                    }
                }
            }
        }
    }
}

fun getCurrentDate(): String {
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(calendar.time)
}

@Composable
fun EventCard(event: Event) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${event.name}", fontWeight = FontWeight.Bold)
            Text("Duration: ${event.duration}", fontWeight = FontWeight.Normal)
            Text("Venue: ${event.venue}", fontWeight = FontWeight.Normal)
            Text("Date: ${event.date}", fontWeight = FontWeight.Normal)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEventInputForm() {
    EventInputForm()
}
