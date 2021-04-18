package com.example.todolist

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TaskList (private val context: Activity, internal var tasks: List<Task>) : ArrayAdapter<Task>(context, R.layout.activity_task_description, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.activity_task_description, null, true)

        val textViewTitle = listViewItem.findViewById(R.id.taskName) as TextView
        val textViewContent = listViewItem.findViewById(R.id.taskDescription) as TextView

        val artist = tasks[position]
        textViewTitle.text = artist.title
        textViewContent.text = artist.content

        return listViewItem
    }
}