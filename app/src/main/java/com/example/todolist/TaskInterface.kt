package com.example.todolist

import com.example.todolist.Task
import retrofit2.Call
import retrofit2.http.*


// Endpoints are defined inside this interface using
// special Retrofit annotations to encode details about
// the parameters and request method.
interface TaskInterface {

    @GET("index.php")
    fun getAllTasks(): Call<List<Task>>

    @POST("index.php")
    fun createTask(@Body task: Task):Call<Task>

    @PUT("index.php/?")
    fun editTask(@Query("id") id:Int?, @Body task: Task):Call<Task>

    @DELETE("index.php/?")
    fun deleteTask(@Query("id") id:Int):Call<String>
}