package com.example.todolist

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class TaskList (private val context: Activity, internal var tasks: List<Task>) : ArrayAdapter<Task>(context, R.layout.layout_list_task, tasks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_task, null, true)

        val textViewTitle = listViewItem.findViewById(R.id.textViewTitle) as TextView
        val textViewContent = listViewItem.findViewById(R.id.textViewContent) as TextView

        val artist = tasks[position]
        textViewTitle.text = artist.title
        textViewContent.text = artist.content

        return listViewItem
    }
}