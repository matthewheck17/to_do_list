package com.example.todolist

// Model class
data class Task(
    var task_id: Int?=0,
    var title: String?="",
    var content : String?="",
    var completed : Boolean?=false) {
}