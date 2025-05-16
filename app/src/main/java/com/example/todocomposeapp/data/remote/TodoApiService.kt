package com.example.todocomposeapp.data.remote

import com.example.todocomposeapp.data.model.Todo
import retrofit2.Response
import retrofit2.http.*

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}