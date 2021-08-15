package com.example.screenaddiction

import android.content.Context
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.*

val COUNTER_DATE = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
val DEFAULT_VALUE = 0
val DATABASE = "DATABASE"

class DatabaseHandles {
    fun saveData(context: Context, counter : Int ) {

        //create a sharedPref based on todays date.
        context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)
            .edit()
            .putInt(COUNTER_DATE,counter)
            .apply()

    }
    fun loadData(context: Context) : SharedPreferences {
        return context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)
    }

    fun getScreenOns(context: Context) : Int {
        return loadData(context).getInt(COUNTER_DATE, DEFAULT_VALUE)
    }
     fun getAllEntries(context: Context) : MutableMap<String, Any?> {

         val hMap: MutableMap<String, Any?> = LinkedHashMap()
         context?.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)?.all?.forEach {
             hMap[it.key] = it.value
         }
         return hMap
     }


}