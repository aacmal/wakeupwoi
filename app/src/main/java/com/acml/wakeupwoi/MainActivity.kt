package com.acml.wakeupwoi

import com.acml.wakeupwoi.ui.screens.alarm.AlarmScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.acml.wakeupwoi.ui.screens.alarmdetail.AlarmDetailScreen
import com.acml.wakeupwoi.ui.theme.WakeupwoiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WakeupwoiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = {
                            TopAppBar(title = {
                                Text(text = "Wakeupwoi")
                            })
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "alarm",
                            modifier = Modifier.padding(it)
                        ) {
                            composable("alarm") {
                                AlarmScreen(
                                    onClickAlarm = { id ->
                                        navController.navigate("alarm/${id}")
                                    }
                                )
                            }
                            composable("alarm/{id}") { backStackEntry ->
                                val id = backStackEntry.arguments?.getString("id")?.toInt()
                                AlarmDetailScreen(
                                    onClickUpdate = {
                                        navController.popBackStack()
                                    }
                                )
                            }
                        }
                    }
//                    Greeting("Android")

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WakeupwoiTheme {
        Greeting("Android")
    }
}