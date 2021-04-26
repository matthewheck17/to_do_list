package com.example.todolist

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_task_description.view.*
import kotlinx.android.synthetic.main.homeview.*
import kotlinx.android.synthetic.main.homeview.view.*

class MainActivity : AppCompatActivity(), TaskAdapter.TaskListener {

    private lateinit var vm:TaskViewModel
    private lateinit var adapter: TaskAdapter

    // Initialize our Recyclerview Adapter and display the fetched result.
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
                it.reverse() // Most recent task is listed first.
            }
            progress_home.visibility = View.GONE // Loading screen is now gone


        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    // When the menu is selected, users are able to create
    // a new task.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_create_task -> showCreateTaskDialog()
        }
        return true
    }

    // CreateTaskDialog contains the task title
    // and task content. User must include both
    // to create a new task.
    private fun showCreateTaskDialog() {

        val dialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.activity_task_description, null)
        dialog.setContentView(view)

        var title = ""
        var content = ""

        view.btn_submit.setOnClickListener {
            title = view.title.text.toString().trim()
            content = view.content.text.toString().trim()

            if (title.isNotEmpty() && content.isNotEmpty()) {
                val task = Task()
                task.task_id = 1
                task.title = title
                task.content = content

                vm.createTask(task)

                vm.createTaskLiveData?.observe(this, Observer {
                        adapter.addTask(task) // Adding task to list
                        home.smoothScrollToPosition(0)
                        dialog.cancel()
                })
            }
            else {
                showToast("You must enter in a task title and content!")
            }
        }

        dialog.show()

        val window = dialog.window
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }


    private fun initAdapter() {

        adapter = TaskAdapter(this)
        home.layoutManager = LinearLayoutManager(this)
        home.adapter = adapter
    }

    override fun onItemDeleted(task: Task, position: Int) {

        task.task_id?.let { vm.deleteTask(it) }
        vm.deleteTaskLiveData?.observe(this, Observer {
            if (it != null) {
                adapter.removeTask(position)
            }
        })
    }

    override fun onItemCompleted(task: Task, position: Int) {

        task.complete()
        task.title?.let { vm.editTask(task) }
        task.content?.let { vm.editTask(task)}
        vm.editTaskLiveData?.observe(this, Observer {
            if (it != null) {
                adapter.editTask(task)
            }
        })
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

/*

     private fun updateTask() {

        val dialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.homeview, null)
        dialog.setContentView(view)

        val title = findViewById<TextView>(R.id.taskTitle)
        val content = findViewById<TextView>(R.id.taskContent)

        title.setFocusable(true);
        title.setEnabled(true);
        title.setClickable(true);
        title.setFocusableInTouchMode(true);

        content.setFocusable(true);
        content.setEnabled(true);
        content.setClickable(true);
        content.setFocusableInTouchMode(true);


        view.visibility = View.GONE

        title.requestFocus()
        content.requestFocus()

        // Show the keyboard.
        val kb = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        kb.showSoftInput(title, 0)
        kb.showSoftInput(content, 0)
    }
 */
