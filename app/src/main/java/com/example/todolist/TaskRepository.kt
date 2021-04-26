package com.example.todolist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Contains the functions to get all tasks,
// create a task, delete a task, and edit
// a task.
class TaskRepository {

    private var taskInterface:TaskInterface?=null

    init {
        taskInterface = TaskClient.getClient().create(TaskInterface::class.java)
    }

    // Function to fetch all of the tasks.
    // Called when the page first loads.
    fun getAllTasks(): LiveData<List<Task>> {
        val data = MutableLiveData<List<Task>>()

        taskInterface?.getAllTasks()?.enqueue(object : Callback<List<Task>> {

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<Task>>,
                response: Response<List<Task>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }
                else {
                    data.value = null
                }
            }
        })

        return data
    }

    // Function to create a task
    fun createTask(task: Task):LiveData<Task>{
        val data = MutableLiveData<Task>()

        taskInterface?.createTask(task)?.enqueue(object : Callback<Task>{
            override fun onFailure(call: Call<Task>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                val res = response.body()
                if (response.code() == 201 && res!=null){
                    data.value = res
                }
                else {
                    data.value = null
                }
            }
        })

        return data
    }

    // Function to edit a task
    fun editTask(task: Task):LiveData<Task> {

        val data = MutableLiveData<Task>()
        task.complete()

        taskInterface?.editTask(task.getID(), task)?.enqueue(object : Callback<Task> {
            override fun onFailure(call: Call<Task>, t: Throwable) {
                data.value = null
            }
            override fun onResponse(call: Call<Task>, response: Response<Task>) {
                val res = response.body()
                if (response.code() == 201 && res!=null){
                    data.value = res
                }else{
                    data.value = null
                }
            }
        })

        return data
    }


    // Function to delete a task
    fun deleteTask(task_id:Int):LiveData<Boolean>{
        val data = MutableLiveData<Boolean>()

        taskInterface?.deleteTask(task_id)?.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                data.value = response.code() == 200
            }
        })

        return data
    }
}