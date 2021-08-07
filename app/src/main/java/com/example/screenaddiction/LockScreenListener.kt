package com.example.screenaddiction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class LockScreenListener : BroadcastReceiver() {
    //Create a key which matches todays date
    val COUNTER_DATE = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
    val DEFAULT_VALUE = 0
    val DATABASE = "DATABASE"


    private fun saveData(context: Context, counter : Int ) {

        //create a sharedPref based on todays date.
        context.getSharedPreferences(DATABASE, MODE_PRIVATE)
            .edit()
            .putInt(COUNTER_DATE,counter)
            .apply()

    }

    private fun loadData(context: Context) : SharedPreferences {
        return context.getSharedPreferences(DATABASE, MODE_PRIVATE)
    }

    fun getScreenOns(context: Context) : Int {
        return loadData(context).getInt(COUNTER_DATE, DEFAULT_VALUE)
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