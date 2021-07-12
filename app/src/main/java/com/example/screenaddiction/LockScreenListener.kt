package com.example.screenaddiction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import java.text.SimpleDateFormat
import java.util.*

const val COUNTER_KEY = "counter"
const val DEFAULT_VALUE = 0


class LockScreenListener : BroadcastReceiver() {



    private fun saveData(context: Context, counter : Int ) {
        //Create a key which matches todays date
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())

        //create a sharedPref based on todays date.
        context.getSharedPreferences(currentDate, MODE_PRIVATE)
            .edit()
            .putInt(COUNTER_KEY,counter)
            .apply()

    }

    private fun loadData(context: Context) : SharedPreferences {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(Date())
        return context.getSharedPreferences(currentDate, Context.MODE_PRIVATE)
    }

    fun getScreenOns(context: Context) : Int
    {
        return loadData(context).getInt(COUNTER_KEY, DEFAULT_VALUE)
    }




    override fun onReceive(context: Context?, intent: Intent?) {
        val screenOn = intent?.action
        when (screenOn){
            Intent.ACTION_SCREEN_ON -> {
                Toast.makeText(context,"wolololo", Toast.LENGTH_SHORT)

                if (context != null){
                    val counter = getScreenOns(context) + 1
                    println(counter)
                    saveData(context, counter)

                }


                
            }
        }
    }







//https://stackoverflow.com/questions/47742474/kotlin-call-a-function-to-update-ui-from-broadcastreceiver-onreceive

}