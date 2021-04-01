package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arrayAdapter: ArrayAdapter<*>
        val tasks = arrayOf("Pass Advanced Internet Concepts", "Buy face care stuff", "Buy Jackie a heat lamp", "Bake a cake", "Move in to new house")

        // access the listView from xml file
        var tasksList = findViewById<ListView>(R.id.tasksList)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks)
        tasksList.adapter = arrayAdapter

        // When the user presses the add button they are redirected
        // to the "Add a New Task" page. On this page the user can
        // add a new task and add the description.
        val add = findViewById<FloatingActionButton>(R.id.add)
        add.setOnClickListener{
            val intent = Intent(this, taskDescription::class.java)
            startActivity(intent)
        }

        // Delete button
        val delete = findViewById<FloatingActionButton>(R.id.delete)
        delete.setOnClickListener {
            // We are showing only toast message. However, you can do anything you need.
            Toast.makeText(applicationContext, "This is the delete button", Toast.LENGTH_SHORT).show()
        }
    }
}