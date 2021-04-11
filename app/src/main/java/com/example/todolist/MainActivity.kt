package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayAdapter: ArrayAdapter<*>
        val tasks = arrayOf(
            "Pass Advanced Internet Concepts",
            "Buy face care stuff",
            "Buy Jackie a heat lamp",
            "Bake a cake",
            "Move in to new house"
        )

        // access the listView from xml file
        var tasksList = findViewById<ListView>(R.id.tasksList)


        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, tasks)
        tasksList.adapter = arrayAdapter

        // Adds checkbox next to each item on the list.
        // By default, nothing is checked
        fun checkbox() {
            tasksList.choiceMode = ListView.CHOICE_MODE_MULTIPLE
            val cntChoice = tasksList.getCount()

            for (i in 0 until cntChoice) {
                tasksList.setItemChecked(i,false)
            }
        }
        // When the user presses the add button they are redirected
        // to the "Add a New Task" page. On this page the user can
        // add a new task and add the description.
        fun addTask() {
            val add = findViewById<FloatingActionButton>(R.id.add)
            add.setOnClickListener {
                val intent = Intent(this, taskDescription::class.java)
                startActivity(intent)
            }
        }

        // Delete button
        fun deleteTask() {
            val delete = findViewById<FloatingActionButton>(R.id.delete)
            delete.setOnClickListener {
                // We are showing only toast message. However, you can do anything you need.
                Toast.makeText(applicationContext, "This is the delete button", Toast.LENGTH_SHORT).show()
            }
        }
    }
}