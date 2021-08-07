package com.example.screenaddiction

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

//No idea wtf this class is made of
class CounterViewModel(private val context: Application) : AndroidViewModel(context), SharedPreferences.OnSharedPreferenceChangeListener {


    private val _counter = MutableLiveData<Int>(0)
    val counter : LiveData<Int> = _counter

    //When the CounterViewModel is initialized, get the current number of screen
    init {
        _counter.value = getScreenOns()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        context.getSharedPreferences(currentDate, Context.MODE_PRIVATE)
            .unregisterOnSharedPreferenceChangeListener(this)

        context.getSharedPreferences(currentDate, Context.MODE_PRIVATE)
            .registerOnSharedPreferenceChangeListener(this)
    }

    private fun loadData() : SharedPreferences {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        return context.getSharedPreferences(currentDate, Context.MODE_PRIVATE)
    }

    fun getScreenOns() : Int
    {
        return loadData().getInt(COUNTER_DATE, DEFAULT_VALUE)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == COUNTER_DATE){
            _counter.value = getScreenOns()
        }
    }
}