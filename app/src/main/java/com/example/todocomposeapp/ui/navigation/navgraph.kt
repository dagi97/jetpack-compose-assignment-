package com.example.todocomposeapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost as ComposeNavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todocomposeapp.data.repository.TodoRepository
import com.example.todocomposeapp.ui.screens.TodoDetailScreen
import com.example.todocomposeapp.ui.screens.TodoListScreen
import com.example.todocomposeapp.viewmodel.TodoDetailViewModel
import com.example.todocomposeapp.viewmodel.TodoListViewModel

@Composable
fun NavHost(
    repository: TodoRepository,
    isDarkMode: Boolean,
    onThemeUpdated: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    ComposeNavHost(navController = navController, startDestination = "list") {
        composable("list") {
            val viewModel: TodoListViewModel = viewModel(factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                    return TodoListViewModel(repository) as T
                }
            })
            TodoListScreen(
                navController = navController,
                viewModel = viewModel,
                isDarkMode = isDarkMode,
                onThemeUpdated = onThemeUpdated
            )
        }
        composable(
            "detail/{todoId}",
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
            val viewModel: TodoDetailViewModel = viewModel(factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                    return TodoDetailViewModel(repository, todoId) as T
                }
            })
            TodoDetailScreen(
                navController = navController,
                viewModel = viewModel,
                todoId = todoId
            )
        }
    }
} 