package com.example.TodoApp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

//controls save and loading of data (todos)
class DataControl {
    //shared preferences consts


    companion object {
        val SHARED_PREFS : String = "Shared Preferences"
        val TODO_SAVE_KEY : String = "Todos"

        fun saveTodos(context: Context, todoList: ArrayList<Todo>) {
            //save todos in shared preferences
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()

            //use Gson to convert arraylist to string
            val gson: Gson = Gson()
            val jsonString: String = gson.toJson(todoList)

            //save string version of data
            editor.putString(TODO_SAVE_KEY, jsonString)
            editor.apply()
        }

        fun loadTodos(context: Context): ArrayList<Todo> {
            //load todos from shared preferences
            val sharedPreferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
            //nullable string
            val jsonString: String = sharedPreferences.getString(TODO_SAVE_KEY, "") as String

            //use Gson to convert string to Arraylist
            val gson: Gson = Gson()
            val type = object : TypeToken<List<Todo>>() {}.type

            //if string is empty, black arrayList is assigned to todoList
            val todoList: ArrayList<Todo> = if (jsonString == "") {
                ArrayList<Todo>()
            } else {
                gson.fromJson(jsonString, type)
            }

            return todoList
        }
    }

}