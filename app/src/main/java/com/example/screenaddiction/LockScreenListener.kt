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


    private val dataBase = DatabaseHandles()

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action){
            Intent.ACTION_SCREEN_ON -> {
                Toast.makeText(context,"wolololo", Toast.LENGTH_SHORT).show()

                if (context != null){
                    val counter = dataBase.getScreenOns(context) + 1
                    println(counter)
                    dataBase.saveData(context, counter)

                }


                
            }
        }
    }







//https://stackoverflow.com/questions/47742474/kotlin-call-a-function-to-update-ui-from-broadcastreceiver-onreceive

}