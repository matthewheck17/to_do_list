package com.example.todolist

object EndPoints {
    private val URL_ROOT = "https://10.0.2.2:8888/to_do_list/web/backend/services/services/"
    val URL_ADD_TASK = URL_ROOT + "createTask.php"
    val URL_EDIT_TASK = URL_ROOT + "EditTask.php"
    val URL_GET_TASK = URL_ROOT + "getAllTasks.php"
    val URL_GET_SINGLE_TASK = URL_ROOT + "getSingleTasks.php"
}