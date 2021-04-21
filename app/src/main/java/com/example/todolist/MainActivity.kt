package com.example.todolist

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_task_description.view.*


class MainActivity : AppCompatActivity(), TaskServiceFactory.TaskListener {

    private lateinit var vm:TaskViewModel
    private lateinit var adapter: TaskServiceFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(this.application).create(TaskViewModel::class.java)

        initAdapter()

        vm.getAllTasks()

        vm.taskModelListLiveData?.observe(this, Observer {
            if (it != null) {
                home.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<Task>)
            }
            else {
                showToast("There are currently no tasks.")
            }
            progress_home.visibility = View.GONE
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_create_task -> showCreateTaskDialog()
        }
        return true
    }

    private fun showCreateTaskDialog() {
        val dialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.activity_task_description, null)
        dialog.setContentView(view)

        var title = ""
        var content = ""


            view.btn_submit.setOnClickListener {
            title = view.et_title.text.toString().trim()
            content = view.et_content.text.toString().trim()

            if (title.isNotEmpty() && content.isNotEmpty()){
                val task = Task()
                task.task_id = 0
                task.title = title
                task.content = content
                task.completed = false

                vm.createTask(task)

                vm.createTaskLiveData?.observe(this, Observer {
                    if (it != null) {
                        adapter.addData(task)
                        home.smoothScrollToPosition(0)
                    } else {
                        showToast("Your task has not been added.")
                    }
                    dialog.cancel()
                })

            }else{
                showToast("You must enter in a task title and content!")
            }

        }

        dialog.show()

        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

    }

    private fun initAdapter() {
        adapter = TaskServiceFactory(this)
        home.layoutManager = LinearLayoutManager(this)
        home.adapter = adapter
    }

    override fun onItemDeleted(task: Task, position: Int) {
        task.task_id?.let { vm.deleteTask(it) }
        vm.deleteTaskLiveData?.observe(this, Observer {
            if (it != null) {
                adapter.removeData(position)
            } else {
                showToast("Cannot delete post at the moment!")
            }
        })

    }

    private fun showToast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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