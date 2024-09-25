package com.example.campusconnect.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.campusconnect.App

data class ImagePost(
    val imageUrl: String,
    val description: String,
    val comments: MutableList<String> = mutableListOf()
)

data class Poll(
    val question: String,
    val options: List<String>,
    var votes: MutableList<Int> = MutableList(options.size) { 0 }
)

@Composable
fun PostImagePage() {
    var imageUrl by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    val posts = remember { mutableStateListOf<ImagePost>() }
    var pollQuestion by remember { mutableStateOf(TextFieldValue("")) }
    var pollOptions by remember { mutableStateOf(TextFieldValue("")) }
    val polls = remember { mutableStateListOf<Poll>() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Post an Image", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = imageUrl,
            onValueChange = { imageUrl = it },
            label = { Text("Image URL") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (imageUrl.text.isNotBlank() && description.text.isNotBlank()) {
                    posts.add(ImagePost(imageUrl.text, description.text))
                    imageUrl = TextFieldValue("") // Clear input
                    description = TextFieldValue("") // Clear input
                }
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Post Image")
        }

        // Display the posts
        posts.forEach { post ->
            ImageCard(post) { commentText ->
                post.comments.add(commentText)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Create a Poll", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        OutlinedTextField(
            value = pollQuestion,
            onValueChange = { pollQuestion = it },
            label = { Text("Poll Question") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = pollOptions,
            onValueChange = { pollOptions = it },
            label = { Text("Options (comma separated)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (pollQuestion.text.isNotBlank() && pollOptions.text.isNotBlank()) {
                    val optionsList = pollOptions.text.split(",").map { it.trim() }
                    polls.add(Poll(pollQuestion.text, optionsList))
                    pollQuestion = TextFieldValue("")
                    pollOptions = TextFieldValue("")
                }
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Create Poll")
        }

        // Display the polls
        polls.forEach { poll ->
            PollCard(poll)
        }
    }
}

@Composable
fun ImageCard(post: ImagePost, onCommentAdded: (String) -> Unit) {
    val painter = rememberAsyncImagePainter(post.imageUrl)

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        // Image display
        Image(
            painter = painter,
            contentDescription = "Image Post",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Text("Description: ${post.description}", fontWeight = FontWeight.Bold)

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
                    onCommentAdded(commentText.text)
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
fun PollCard(poll: Poll) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(poll.question, fontWeight = FontWeight.Bold)

        val totalVotes = poll.votes.sum()
        poll.options.forEachIndexed { index, option ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(option)
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        poll.votes[index] += 1 // Increment vote count
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text("Vote")
                }
                if (totalVotes > 0) {
                    Text("Votes: ${poll.votes[index]} (${(poll.votes[index].toFloat() / totalVotes * 100).toInt()}%)")
                }
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostImagePage() {
    PostImagePage() // Preview of your PostImagePage
}
