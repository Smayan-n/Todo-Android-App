package com.example.TodoApp

import java.io.Serializable

interface RecyclerViewInterface : Serializable {
    fun onItemDelete(position: Int, confirmDelete : Boolean)
    fun onItemEdit(position: Int)
    fun onItemClick(position: Int)
}