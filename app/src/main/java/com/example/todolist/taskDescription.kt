package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONException
import org.json.JSONObject

class taskDescription : AppCompatActivity() {

    private var taskName: EditText? = null
    private var taskDescription: EditText? = null
    private var addNewTaskTitle: TextView? = null
    private var saveButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_description)


        taskName = findViewById(R.id.taskName)
        taskDescription = findViewById(R.id.taskDescription)
        addNewTaskTitle = findViewById(R.id.addNewTaskTitle)
        saveButton = findViewById(R.id.saveButton)

        //adding a click listener to button
        val save = findViewById<Button>(R.id.saveButton)

        save.setOnClickListener {
            addTask()
        }

        save.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    //adding a new task to database
    private fun addTask() {

        //getting the task values
        val title = taskName?.text.toString()
        val content = taskDescription?.text.toString()


        //creating volley string request
        val stringRequest = object : StringRequest(Request.Method.POST, EndPoints.URL_ADD_TASK,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["title"] = title
                params["content"] = content
                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }
}
