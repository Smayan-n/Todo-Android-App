package com.example.TodoApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class TodoAdapter(context: Context, todoList: ArrayList<Todo>, recyclerViewInterface: RecyclerViewInterface) :
    RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    val todoList = todoList
    val context = context
    val recyclerViewInterface = recyclerViewInterface


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.MyViewHolder {
        //this is where you inflate the layout (give a look to out rows)
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.todo_row, parent, false)

        return TodoAdapter.MyViewHolder(view, recyclerViewInterface)
    }

    override fun onBindViewHolder(holder: TodoAdapter.MyViewHolder, position: Int) {
        //assigning values to the views we created in the todo_row layout based on pos of recycler view
        val todo: Todo = todoList[position]
        holder.todoTitle.text = todo.todoTitle
        holder.tvTime.text = todo.timeCreatred

    }

    override fun getItemCount(): Int {
        //recycler view wants to know the number of rows
        return todoList.size
    }

    //nested class
    class MyViewHolder(itemView: View, recyclerViewInterface: RecyclerViewInterface) : RecyclerView.ViewHolder(itemView) {

        val todoTitle: TextView = itemView.findViewById(R.id.todoTitle)
        val tvTime : TextView = itemView.findViewById(R.id.tvTime)
        val editBtn: Button = itemView.findViewById(R.id.editBtn)
        val deleteBtn: Button = itemView.findViewById(R.id.deleteBtn)


        init{
           //delete item on long click
            deleteBtn.setOnClickListener {
                recyclerViewInterface.onItemDelete(adapterPosition, true)
            }

            //edit button
            editBtn.setOnClickListener {
                //first save ref to todo
                recyclerViewInterface.onItemEdit(adapterPosition)

            }

            itemView.setOnClickListener {
                recyclerViewInterface.onItemClick(adapterPosition)
            }

        }

    }

}