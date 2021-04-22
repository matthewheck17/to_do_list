package com.example.todolist

import com.example.todolist.Task
import retrofit2.Call
import retrofit2.http.*

interface TaskService {

    @GET("index.php")
    fun getAllTasks(): Call<List<Task>>

    @POST("index.php")
    fun createTask(@Body task: Task):Call<Task>

    @PUT("index.php")
    fun editTask(@Body task: Task):Call<Task>

     @DELETE("index.php/{id}")
    fun deleteTask(@Path("id") id:Int):Call<String>
}