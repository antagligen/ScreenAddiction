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
    private val dataBase = DatabaseHandles()

    //When the CounterViewModel is initialized, get the current number of screen
    //Unregister the sharepref listener to avoid doublettes.
    init {
        _counter.value = dataBase.getScreenOns(context)
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)
            .unregisterOnSharedPreferenceChangeListener(this)

        context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == COUNTER_DATE){
            _counter.value = dataBase.getScreenOns(context)
        }
    }
}