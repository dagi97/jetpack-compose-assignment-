package com.example.todocomposeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocomposeapp.data.local.TodoEntity
import com.example.todocomposeapp.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoEntity>>(emptyList())
    val todos: StateFlow<List<TodoEntity>> = _todos

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isFromCache = MutableStateFlow(false)
    val isFromCache: StateFlow<Boolean> = _isFromCache

    init {
        viewModelScope.launch {
            try {
                _todos.value = repository.fetchTodos()
                _isFromCache.value = false
            } catch (e: Exception) {
                val fallback = repository.getCachedTodos()
                if (fallback.isNotEmpty()) {
                    _todos.value = fallback
                    _isFromCache.value = true
                } else {
                    _error.value = "Failed to load data. No cache available."
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}