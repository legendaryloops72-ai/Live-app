package com.livepractice.simulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.livepractice.simulator.ui.screens.HistoryScreen
import com.livepractice.simulator.ui.screens.HomeScreen
import com.livepractice.simulator.ui.screens.LiveStreamScreen
import com.livepractice.simulator.ui.screens.SessionSummaryScreen
import com.livepractice.simulator.ui.theme.LivePracticeTheme
import com.livepractice.simulator.ui.viewmodel.LiveStreamViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LivePracticeTheme {
                val navController = rememberNavController()
                val viewModel: LiveStreamViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(
                            viewModel = viewModel,
                            onStartStream = {
                                navController.navigate("live")
                            },
                            onNavigateToHistory = {
                                navController.navigate("history")
                            }
                        )
                    }

                    composable("live") {
                        LiveStreamScreen(
                            viewModel = viewModel,
                            onEndStream = {
                                navController.navigate("summary") {
                                    popUpTo("live") { inclusive = true }
                                }
                            }
                        )
                    }

                    composable("summary") {
                        SessionSummaryScreen(
                            viewModel = viewModel,
                            onNavigateHome = {
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                            },
                            onNavigateHistory = {
                                navController.navigate("history")
                            }
                        )
                    }

                    composable("history") {
                        HistoryScreen(
                            viewModel = viewModel,
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
