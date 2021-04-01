package com.example.todolist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class taskDescription : AppCompatActivity() {

    // These are the global variables
    lateinit var taskName: EditText
    lateinit var taskDescription: EditText
    lateinit var addNewTaskTitle: TextView
    lateinit var saveButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_description)

        taskName = findViewById(R.id.taskName)
        taskDescription = findViewById(R.id.taskDescription)
        addNewTaskTitle = findViewById(R.id.addNewTaskTitle)
        saveButton = findViewById(R.id.saveButton)

        // Set the focus to the edit text.
        taskName.requestFocus()
        taskDescription.requestFocus()
    }
}