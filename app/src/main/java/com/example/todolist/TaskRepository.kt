package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository     {

private var taskService:TaskService?=null

init {
    taskService = TaskClient.getClient().create(TaskService::class.java)
}

fun getAllTasks(): LiveData<List<Task>> {
    val data = MutableLiveData<List<Task>>()

    taskService?.getAllTasks()?.enqueue(object : Callback<List<Task>> {

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
            }else{
                data.value = null
            }

        }
    })

    return data

}

fun createTask(task: Task):LiveData<Task>{
    val data = MutableLiveData<Task>()

    taskService?.createTask(task)?.enqueue(object : Callback<Task>{
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


fun deleteTask(id:Int):LiveData<Boolean>{
    val data = MutableLiveData<Boolean>()

    taskService?.deleteTask(id)?.enqueue(object : Callback<String>{
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