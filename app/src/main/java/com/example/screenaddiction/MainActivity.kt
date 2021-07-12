package com.example.screenaddiction

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.ViewModel


class MainActivity : AppCompatActivity() {

    val currentSdk = android.os.Build.VERSION.SDK_INT
    //Create the ViewModel
    private val model: CounterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ForegroundService.startService(this, "wolololo")
        //Observe the counter in the viewmodel and update the GUI with the value.
        model.counter.observe(this) {
            findViewById<TextView>(R.id.uiScreenCounter).text = it.toString()

        }

    }

}




