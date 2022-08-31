package com.example.TodoApp

import java.io.Serializable

data class Todo(
    var todoTitle: String,
    val timeCreatred : String
) : Serializable //could be passed in new activity
