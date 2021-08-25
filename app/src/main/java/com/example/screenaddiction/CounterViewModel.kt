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

    private val dataBase = DatabaseHandles()

    private val _totalCounter = MutableLiveData(dataBase.getScreenOns(context))
    val totalCounter : LiveData<Int> = _totalCounter

    private val _averageCounter = MutableLiveData(dataBase.getAverageEntry(context))
    val averageCounter : LiveData<Int> = _averageCounter

    private val _lowestCounter = MutableLiveData(dataBase.getLowestEntry(context))
    val lowestCounter : LiveData<DateValue> = _lowestCounter

    private val _highestCounter = MutableLiveData(dataBase.getHighestEntry(context))
    val highestCounter : LiveData<DateValue> = _highestCounter


    //When the CounterViewModel is initialized, get the current number of screen
    //Unregister the sharepref listener to avoid doublettes.
    init {
        _totalCounter.value = dataBase.getScreenOns(context)
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)
            .unregisterOnSharedPreferenceChangeListener(this)

        context.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)
            .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == COUNTER_DATE){
            _totalCounter.value = dataBase.getScreenOns(context)
            _averageCounter.value = dataBase.getAverageEntry(context)
            _lowestCounter.value = dataBase.getLowestEntry(context)
            _highestCounter.value = dataBase.getHighestEntry(context)
        }
    }
}