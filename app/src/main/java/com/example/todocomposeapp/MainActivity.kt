package com.example.todocomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.todocomposeapp.data.local.TodoDatabase
import com.example.todocomposeapp.data.remote.RetrofitClient
import com.example.todocomposeapp.data.repository.TodoRepository
import com.example.todocomposeapp.ui.screens.TodoDetailScreen
import com.example.todocomposeapp.ui.screens.TodoListScreen
import com.example.todocomposeapp.viewmodel.TodoDetailViewModel
import com.example.todocomposeapp.viewmodel.TodoListViewModel
import com.example.todocomposeapp.ui.theme.TodoComposeAppTheme
import com.example.todocomposeapp.ui.navigation.NavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Room database instance
        val db = Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            "todos.db"
        ).build()

        // Shared repository instance
        val repository = TodoRepository(RetrofitClient.apiService, db.todoDao())

        setContent {
            var isDarkMode by remember { mutableStateOf(false) }

            TodoComposeAppTheme(darkTheme = isDarkMode) {
                NavHost(
                    repository = repository,
                    isDarkMode = isDarkMode,
                    onThemeUpdated = { isDarkMode = it }
                )
            }
        }
    }
}