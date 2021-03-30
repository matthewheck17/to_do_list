package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            // Clearing all the items in the list when the clear button is pressed
            clear.setOnClickListener {

                itemlist.clear()
                adapter.notifyDataSetChanged()
            }
        }
        *
         */
    }
}