package com.example.campusconnect

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.campusconnect.ui.theme.CampusConnectTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            CampusConnectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    App(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "First", modifier = modifier) {
        composable(route = "First") {
            First(
                navigationToSecond = { navController.navigate("Second") },
                navigationToThird = { navController.navigate("Third") },
                navigationToFourth = { navController.navigate("Fourth") }
            )
        }
        composable(route = "Second") {
            Second(
                navigationToFirst = { navController.navigate("First") }
            )
        }
        composable(route = "Third") {
            Third(
                navigationToFirst = { navController.navigate("First") }
            )
        }
        composable(route = "Fourth") {
            Fourth(
                navigationToFirst = { navController.navigate("First") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    CampusConnectTheme {
        App(modifier = Modifier.fillMaxSize())
    }
}



