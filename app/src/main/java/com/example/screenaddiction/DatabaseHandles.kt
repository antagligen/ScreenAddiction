package com.example.screenaddiction

import android.content.Context
import android.content.SharedPreferences
import java.text.SimpleDateFormat
import java.util.*

val COUNTER_DATE: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
const val DEFAULT_VALUE = 0
const val DATABASE = "DATABASE"
const val LOWEST_VALUE_VAR = 9999

class DatabaseHandles {
    fun saveData(context: Context, counter : Int ) {

        //create a sharedPref with keys based on todays date
        context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)
            .edit()
            .putInt(COUNTER_DATE,counter)
            .apply()

    }
    private fun loadData(context: Context) : SharedPreferences {
        return context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)
    }

    fun getScreenOns(context: Context) : Int {
        return loadData(context).getInt(COUNTER_DATE, DEFAULT_VALUE)
    }
     fun getAllEntries(context: Context) : MutableMap<String, Any?> {

         val hMap: MutableMap<String, Any?> = LinkedHashMap()
         context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)?.all?.forEach {
             hMap[it.key] = it.value
         }
         return hMap
     }

    fun getHighestEntry(context: Context) : MutableMap<String, Any?>{

        var highestValue = 0
        var highestKey = ""
        context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)?.all?.forEach {
            if (it.value != null && it.value.toString().toInt() > highestValue){
                highestValue = it.value.toString().toInt()
                highestKey = it.key
            }

        }
        val hMap: MutableMap<String, Any?> = LinkedHashMap()
        hMap[highestKey] = highestValue
        return hMap

    }

    fun getLowestEntry(context: Context) : MutableMap<String, Any?>{
        var lowestValue = LOWEST_VALUE_VAR
        var lowestKey = ""
        context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)?.all?.forEach {
            if (it.value != null && it.value.toString().toInt() < lowestValue){
                lowestValue = it.value.toString().toInt()
                lowestKey = it.key
            }
        }
        val hMap: MutableMap<String, Any?> = LinkedHashMap()
        hMap[lowestKey] = lowestValue
        return hMap
    }

    fun getAverageEntry(context: Context) : Int{
        var sum = 0
        context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)?.all?.forEach {
            sum += it.value.toString().toInt()
        }

        var numberOfEntries = context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE).all?.size
        return sum/ numberOfEntries!!
    }

}