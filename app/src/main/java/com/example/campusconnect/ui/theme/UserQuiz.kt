package com.example.campusconnect.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.campusconnect.R // Ensure this import is correct

// Quiz Display Screen Composable
@Composable
fun QuizDisplayScreen(quizTitle: String, questions: List<QuizQuestionState>, onSubmit: (Int) -> Unit) {
    var selectedAnswers by remember { mutableStateOf(List(questions.size) { "" }) }
    var resultVisible by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Optional: A solid color behind the image
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.bgm2), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text(quizTitle, style = MaterialTheme.typography.titleLarge, color = Color.White)

            Spacer(modifier = Modifier.height(16.dp))

            questions.forEachIndexed { index, questionState ->
                QuestionDisplay(index + 1, questionState, selectedAnswers[index]) { answer ->
                    selectedAnswers = selectedAnswers.toMutableList().apply {
                        this[index] = answer
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(onClick = {
                score = selectedAnswers.zip(questions).count { (selected, question) ->
                    selected == question.correctAnswer
                }
                resultVisible = true
                onSubmit(score) // Call the onSubmit callback with the score
            }) {
                Text("Submit Answers")
            }

            if (resultVisible) {
                Text("Your Score: $score/${questions.size}", color = Color.White)
            }
        }
    }
}

// Question Display Composable
@Composable
fun QuestionDisplay(questionNumber: Int, questionState: QuizQuestionState, selectedAnswer: String, onAnswerSelected: (String) -> Unit) {
    Text("Question $questionNumber: ${questionState.questionText}", color = Color.White)

    Column {
        questionState.options.forEachIndexed { index, option ->
            val optionLetter = ('A' + index).toString()
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selectedAnswer == optionLetter, onClick = { onAnswerSelected(optionLetter) })
                Text("$optionLetter: ${option.value}", color = Color.White) // Access the value of mutableState
            }
        }
    }
}

// Preview Composable
// Preview Composable
@Composable
@Preview
fun PreviewQuizDisplayScreen() {
    val questions = listOf(
        QuizQuestionState().apply {
            questionText = "What is the capital of France?"
            options[0].value = "Berlin"
            options[1].value = "Madrid"
            options[2].value = "Paris"
            options[3].value = "Rome"
            correctAnswer = "C"
        },
        QuizQuestionState().apply {
            questionText = "What is 2 + 2?"
            options[0].value = "3"
            options[1].value = "4"
            options[2].value = "5"
            options[3].value = "6"
            correctAnswer = "B"
        },
        QuizQuestionState().apply {
            questionText = "Which planet is known as the Red Planet?"
            options[0].value = "Earth"
            options[1].value = "Mars"
            options[2].value = "Jupiter"
            options[3].value = "Saturn"
            correctAnswer = "B"
        },
        QuizQuestionState().apply {
            questionText = "What is the largest ocean on Earth?"
            options[0].value = "Atlantic Ocean"
            options[1].value = "Indian Ocean"
            options[2].value = "Arctic Ocean"
            options[3].value = "Pacific Ocean"
            correctAnswer = "D"
        },
        QuizQuestionState().apply {
            questionText = "Who wrote 'Romeo and Juliet'?"
            options[0].value = "Charles Dickens"
            options[1].value = "William Shakespeare"
            options[2].value = "Mark Twain"
            options[3].value = "Jane Austen"
            correctAnswer = "B"
        }
    )

    QuizDisplayScreen(quizTitle = "Sample Quiz", questions = questions) { score ->
        // Handle the score submission here
        println("Score submitted: $score")
    }
}



// QuizQuestionState class
class QuizQuestionState {
    var questionText by mutableStateOf("")
    var options = List(4) { mutableStateOf("") } // Use mutableStateOf for options
    var correctAnswer by mutableStateOf("")
}
