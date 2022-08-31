package com.example.TodoApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ViewTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_todo)
        supportActionBar!!.hide() //hide action bar at the top

        val todo : Todo = intent.getSerializableExtra("TODO") as Todo

        val newTodoTitle = findViewById<TextView>(R.id.newTodoTitle)
        val newtvTime = findViewById<TextView>(R.id.newtvTime)

        newTodoTitle.setText(todo.todoTitle)
        newtvTime.setText("Created on: ${todo.timeCreatred}")


    }

}