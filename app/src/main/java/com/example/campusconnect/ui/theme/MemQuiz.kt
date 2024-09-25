package com.example.campusconnect.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campusconnect.R

// Quiz Creation Screen Composable
@Composable
fun QuizCreationScreen() {
    var quizTitle by remember { mutableStateOf("") }
    val questions = remember { List(5) { QuestionState() } }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Optional: Add a solid color background
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.bgm2), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text("Create Your Quiz", style = MaterialTheme.typography.titleLarge, color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            // Quiz Title Input
            Text("Quiz Title (Required):", color = Color.White)
            TextField(
                value = quizTitle,
                onValueChange = { quizTitle = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )

            Spacer(modifier = Modifier.height(16.dp))

            questions.forEachIndexed { index, questionState ->
                QuestionInput(index + 1, questionState)
                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(onClick = { /* Handle quiz submission */ }) {
                Text("Submit Quiz")
            }
        }
    }
}

// Question Input Composable
@Composable
fun QuestionInput(questionNumber: Int, questionState: QuestionState) {
    var questionText by remember { questionState::questionText }
    var optionA by remember { questionState.options[0] }
    var optionB by remember { questionState.options[1] }
    var optionC by remember { questionState.options[2] }
    var optionD by remember { questionState.options[3] }
    var correctOption by remember { questionState::correctAnswer }

    Column {
        Text("Question $questionNumber (Required):", color = Color.White)
        TextField(
            value = questionText,
            onValueChange = { questionText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.surface)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Option A (Required):", color = Color.White)
        TextField(
            value = optionA,
            onValueChange = { optionA = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp).background(MaterialTheme.colorScheme.surface)
        )

        Text("Option B (Required):", color = Color.White)
        TextField(
            value = optionB,
            onValueChange = { optionB = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp).background(MaterialTheme.colorScheme.surface)
        )

        Text("Option C (Required):", color = Color.White)
        TextField(
            value = optionC,
            onValueChange = { optionC = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp).background(MaterialTheme.colorScheme.surface)
        )

        Text("Option D (Required):", color = Color.White)
        TextField(
            value = optionD,
            onValueChange = { optionD = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp).background(MaterialTheme.colorScheme.surface)
        )

        // Correct Answer Selection
        Text("Select Correct Answer (Required):", color = Color.White)
        Row {
            RadioButton(selected = correctOption == "A", onClick = { correctOption = "A" })
            Text("A", color = Color.White)
            RadioButton(selected = correctOption == "B", onClick = { correctOption = "B" })
            Text("B", color = Color.White)
            RadioButton(selected = correctOption == "C", onClick = { correctOption = "C" })
            Text("C", color = Color.White)
            RadioButton(selected = correctOption == "D", onClick = { correctOption = "D" })
            Text("D", color = Color.White)
        }
    }
}

// Preview Composable
@Composable
@Preview
fun PreviewQuizCreationScreen() {
    QuizCreationScreen()
}

// Question State class
class QuestionState {
    var questionText by mutableStateOf("")
    var options = List(4) { mutableStateOf("") } // Use mutableStateOf for options
    var correctAnswer by mutableStateOf("")
}
