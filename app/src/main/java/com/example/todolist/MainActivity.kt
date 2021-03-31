package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add button
        val add = findViewById<FloatingActionButton>(R.id.add)
        add.setOnClickListener {
            // We are showing only toast message. However, you can do anything you need.
            Toast.makeText(applicationContext, "This is the add button", Toast.LENGTH_SHORT).show()
        }

        // Delete button
        val delete = findViewById<FloatingActionButton>(R.id.delete)
        delete.setOnClickListener {
            // We are showing only toast message. However, you can do anything you need.
            Toast.makeText(applicationContext, "This is the delete button", Toast.LENGTH_SHORT).show()
        }

        // Initializing the array lists and the adapter
        var itemlist = arrayListOf<String>()
        var adapter =ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)


        // Adding the items to the list when the add button is pressed
        add.setOnClickListener {

        }

        // Selecting and deleting the items from the list when the delete button is pressed
        delete.setOnClickListener {

        }

        /*
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            // Initializing the array lists and the adapter
            var itemlist = arrayListOf<String>()
            var adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice, itemlist
            )

            FloatingActionButton add = (FloatingActionButton)
            findViewById(R.id.add);
            add.bringToFront();

            // Adding the items to the list when the add button is pressed
            add.setOnClickListener(new View.OnClickListener() {


                itemlist.add(addNewItem.text.toString())
                listView.adapter = adapter
                adapter.notifyDataSetChanged()
                // This is because every time when you add the item the input space or the eidt text space will be cleared
                editText.text.clear()
            }

        // Selecting and Deleting the items from the list when the delete button is pressed
        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item))
                {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
        }
        *
         */
    }
}