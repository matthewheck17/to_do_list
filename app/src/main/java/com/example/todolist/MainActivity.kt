package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import org.json.JSONException
import org.json.JSONObject
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var listView: ListView? = null
    var tasksList: MutableList<Task>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.tasksList)
        tasksList = mutableListOf<Task>()
        loadTasks()

        val add = findViewById<FloatingActionButton>(R.id.add)
        add.setOnClickListener {
            val intent = Intent(this, taskDescription::class.java)
            startActivity(intent)
        }
        }

         private fun loadTasks() {
            val stringRequest = StringRequest(Request.Method.GET,
                EndPoints.URL_GET_TASK,
                { s ->
                    try {
                        val obj = JSONObject(s)
                            val array = obj.getJSONArray("tasks")

                            for (i in 0 until array.length()) {
                                val objectTask = array.getJSONObject(i)
                                val task = Task(
                                    objectTask.getString("title"),
                                    objectTask.getString("content")
                                )
                                tasksList!!.add(task)
                                val adapter = TaskList(this@MainActivity, tasksList!!)
                                listView!!.adapter = adapter
                            }
                    }
                    catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                { volleyError ->
                    Toast.makeText(
                        applicationContext,
                        volleyError.message,
                        Toast.LENGTH_LONG
                    ).show()
                })

            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add<String>(stringRequest)
        }
    }
        /*

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
        fun addMe() {
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
                // Message
                Toast.makeText(applicationContext, "This is the delete button", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
*
         */