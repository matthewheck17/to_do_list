package com.example.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.TaskRepository
import com.example.todolist.Task

class TaskViewModel (application: Application): AndroidViewModel(application){

    private var taskRepository:TaskRepository?=null

    var taskModelListLiveData : LiveData<List<Task>>?=null
    var createTaskLiveData:LiveData<Task>?=null
    var editTaskLiveData:LiveData<Task>?=null
    var deleteTaskLiveData:LiveData<Boolean>?=null


    init {
        taskRepository = TaskRepository()
        taskModelListLiveData = MutableLiveData()
        createTaskLiveData = MutableLiveData()
        deleteTaskLiveData = MutableLiveData()
        editTaskLiveData = MutableLiveData()
    }

    fun getAllTasks() {
        taskModelListLiveData = taskRepository?.getAllTasks()
    }

    fun createTask(task: Task) {
        createTaskLiveData = taskRepository?.createTask(task)
    }

    fun deleteTask(task_id:Int) {
        deleteTaskLiveData = taskRepository?.deleteTask(task_id)
    }

    fun editTask(task: Task) {
        editTaskLiveData = taskRepository?.editTask(task)
    }
}