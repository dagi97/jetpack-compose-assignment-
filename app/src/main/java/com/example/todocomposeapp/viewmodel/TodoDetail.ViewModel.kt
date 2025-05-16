package com.example.todocomposeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todocomposeapp.data.local.TodoEntity
import com.example.todocomposeapp.data.repository.TodoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TodoDetailViewModel(
    private val repository: TodoRepository,
    private val todoId: Int
) : ViewModel() {

    private val _todo = MutableStateFlow<TodoEntity?>(null)
    val todo: StateFlow<TodoEntity?> = _todo

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isFromCache = MutableStateFlow(false)
    val isFromCache: StateFlow<Boolean> = _isFromCache

    init {
        viewModelScope.launch {
            try {
                val cached = repository.fetchTodos()
                val match = cached.find { it.id == todoId }
                if (match != null) {
                    _todo.value = match
                } else {
                    _error.value = "Todo not found in cache."
                }
            } catch (e: Exception) {
                val fallback = repository.getCachedTodos()
                val match = fallback.find { it.id == todoId }
                if (match != null) {
                    _isFromCache.value = true
                    _todo.value = match
                } else {
                    _error.value = "Todo not found in cache."
                }

            } finally {
                _isLoading.value = false
            }
        }
    }

}