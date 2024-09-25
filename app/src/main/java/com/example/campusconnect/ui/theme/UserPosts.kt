package com.example.campusconnect.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

data class PollPost(
    val pollQuestion: String,
    val pollOptions: List<String>,
    val comments: MutableList<String> = mutableListOf(),
    var pollVotes: MutableMap<String, Int> = mutableMapOf()
)

data class GeneralPost(
    val content: String,
    val comments: MutableList<String> = mutableListOf()
)

@Composable
fun UserMainScreen(pollPosts: List<PollPost>, generalPosts: List<GeneralPost>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Poll Feed", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        pollPosts.forEach { post -> PostCard(post) }

        Spacer(modifier = Modifier.height(16.dp))

        Text("General Posts", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        generalPosts.forEach { post -> GeneralPostCard(post) }
    }
}

@Composable
fun PostCard(post: PollPost) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Poll: ${post.pollQuestion}", fontWeight = FontWeight.Bold)

        post.pollOptions.forEach { option ->
            var selectedOption by remember { mutableStateOf("") }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(option)
                Button(
                    onClick = {
                        post.pollVotes[option] = post.pollVotes.getOrDefault(option, 0) + 1
                        selectedOption = option
                    }
                ) {
                    Text("Vote")
                }
            }
        }

        // Display Poll Results
        Text("Poll Results:", fontWeight = FontWeight.Bold)
        post.pollVotes.forEach { (option, votes) ->
            Text("$option: $votes votes")
        }

        // Comments section
        Text("Comments:", fontWeight = FontWeight.Bold)
        post.comments.forEach { comment ->
            Text("- $comment")
        }

        var commentText by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = commentText,
            onValueChange = { commentText = it },
            label = { Text("Add a Comment") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (commentText.text.isNotBlank()) {
                    post.comments.add(commentText.text)
                    commentText = TextFieldValue("") // Clear input
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Comment")
        }
    }
}

@Composable
fun GeneralPostCard(post: GeneralPost) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text("Post Content: ${post.content}", fontWeight = FontWeight.Bold)

        // Comments section
        Text("Comments:", fontWeight = FontWeight.Bold)
        post.comments.forEach { comment ->
            Text("- $comment")
        }

        var commentText by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(
            value = commentText,
            onValueChange = { commentText = it },
            label = { Text("Add a Comment") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (commentText.text.isNotBlank()) {
                    post.comments.add(commentText.text)
                    commentText = TextFieldValue("") // Clear input
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Comment")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserMainScreen() {
    // Example poll and general post data for preview
    val samplePollPosts = listOf(
        PollPost(
            pollQuestion = "What is your favorite programming language?",
            pollOptions = listOf("Kotlin", "Java", "Python"),
            comments = mutableListOf("I love Kotlin!", "Java is great!")
        )
    )

    val sampleGeneralPosts = listOf(
        GeneralPost(content = "Check out this amazing article!"),
        GeneralPost(content = "Here's a cool picture I took.")
    )

    UserMainScreen(pollPosts = samplePollPosts, generalPosts = sampleGeneralPosts)
}
