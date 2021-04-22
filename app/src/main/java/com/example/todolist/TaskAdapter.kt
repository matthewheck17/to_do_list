package com.example.todolist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.Task
import kotlinx.android.synthetic.main.homeview.view.*

class TaskAdapter (var listener:TaskListener) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var data : ArrayList<Task>?=null

    interface TaskListener {
        fun onItemDeleted(task: Task, position: Int)
    }

    fun setData(list: ArrayList<Task>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.homeview, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bindView(item)
        holder.itemView.img_delete.setOnClickListener {
            item?.let { it1 ->
                listener.onItemDeleted(it1, position)
            }
        }
    }

    fun addTask(task: Task) {
        data?.add(0,task)
        notifyItemInserted(0)
        notifyDataSetChanged()
    }

    fun editTask(task: Task) {
        data?.set(0, task)
        notifyDataSetChanged()
    }

    fun removeTask(position: Int) {
        data?.removeAt(position)
        notifyDataSetChanged()
    }



    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(item: Task?) {
            itemView.Tasktitle.text = item?.title
            itemView.content.text = item?.content
        }

    }

}