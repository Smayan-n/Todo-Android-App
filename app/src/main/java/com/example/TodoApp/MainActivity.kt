package com.example.TodoApp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.TodoApp.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.*

class MainActivity : AppCompatActivity(), RecyclerViewInterface{

    //view binding
    private lateinit var binding: ActivityMainBinding
    //todos list
    private lateinit var todoList: ArrayList<Todo>
    //todo adapter
    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide() //hide action bar at the top

        todoList = DataControl.loadTodos(this)

        //adapter
        todoAdapter = TodoAdapter(this, todoList, this)
        binding.recyclerView.adapter = todoAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        //setting listeners
        binding.addTodoBtn.setOnClickListener {
            addNewTodoItem()
        }


    }

    private fun addNewTodoItem(){
        val todoTitle = binding.todoInput.text.toString()
        val formatter = SimpleDateFormat("MM/dd/yyyy  |  hh:mm a")
        val timeCreated = formatter.format(Date())

        if(todoTitle.length == 0){
            Toast.makeText(this, "Enter a Title", Toast.LENGTH_SHORT).show()
            //exit function
            return
        }
        //is input is not empty
        val todo : Todo = Todo(todoTitle, timeCreated)
        todoList.add(todo)
        todoAdapter.notifyItemInserted(todoList.size - 1)

        //clear input
        binding.todoInput.text.clear()

    }

    override fun onPause() {
        //save data when app closes
        DataControl.saveTodos(this, todoList)
        super.onPause()
    }


    override fun onItemDelete(position: Int, confirmDelete : Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to Delete?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                //when user confirms delete
                todoAdapter.todoList.removeAt(position)
                todoAdapter.notifyItemRemoved(position)
            }
            .setNegativeButton("No") { dialog, id ->
                //else Dismiss the dialog
                dialog.dismiss()
            }
        if (confirmDelete){
            //start dialog
            val alert = builder.create()
            alert.show()
        }
        else{
            todoAdapter.todoList.removeAt(position)
            todoAdapter.notifyItemRemoved(position)
        }

    }

    override fun onItemEdit(position: Int) {
        val todo: Todo = todoList[position]
        //delete todo
        onItemDelete(position, false)

        //set todo title text to input  
        binding.todoInput.setText(todo.todoTitle)

    }

    override fun onItemClick(position: Int) {
        val intent : Intent = Intent(this, ViewTodoActivity::class.java)
        intent.also{
            //pass data to new activity
            it.putExtra("TODO", todoList[position])

            //start activity
            startActivity(it)
        }
    }

}