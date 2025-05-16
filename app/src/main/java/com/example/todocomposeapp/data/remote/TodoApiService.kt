package com.example.todocomposeapp.data.remote

import com.example.todocomposeapp.data.model.Todo

import retrofit2.http.GET as GET1

interface TodoApiService {
    @GET1("todos")
    suspend fun getTodos(): List<Todo>
}