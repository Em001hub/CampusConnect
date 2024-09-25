package com.example.campusconnect.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HelpPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Help Center", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Frequently Asked Questions", style = MaterialTheme.typography.titleMedium)

        // Add FAQ items
        FAQItem(
            question = "How do I book tickets?",
            answer = "To book tickets, go to the Ticket Booking section, select your event, and follow the prompts."
        )
        FAQItem(
            question = "How can I view upcoming events?",
            answer = "You can view upcoming events on the Event Calendar page."
        )
        FAQItem(
            question = "How do I post a comment?",
            answer = "Navigate to the discussion area, select a post, and add your comment in the provided text field."
        )
        FAQItem(
            question = "How can I apply for volunteering opportunities?",
            answer = "Visit the Volunteering section to find available opportunities and application details."
        )
        FAQItem(
            question = "Where can I find member profiles?",
            answer = "Member profiles are showcased in the Members section of the app."
        )

        FAQItem(
            question = "How can I register for event?",
            answer = "Go to tickets button, located on club page and you will find register for event button."
        )

        FAQItem(
            question = "How can I report a problem or provide feedback?",
            answer = "You can report issues or send feedback through the Contact Us section."
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Contact Us", style = MaterialTheme.typography.titleMedium)

        Text("For further assistance, please contact CampusConnect@gmail.com")
    }
}

@Composable
fun FAQItem(question: String, answer: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(question, style = MaterialTheme.typography.bodyMedium)
        Text(answer, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(start = 16.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHelpPage() {
    HelpPage()
}

