package com.example.screenaddiction

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    val currentSdk = android.os.Build.VERSION.SDK_INT
    //Create the ViewModel
    private val model: CounterViewModel by viewModels()






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find the navigation menu and create the listener to switch between fragments based on clicks.
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home-> {
                    loadFragment(HomeFragment())
                }
                R.id.nav_chart-> {
                    loadFragment(ChartFragment())
                }
                R.id.nav_calendar-> {
                    loadFragment(CalendarFragment())
                }
            }
            false

        }
        //Start the foreground service to make us able to count while the app is turned off.
        ForegroundService.startService(this, "wolololo")

        //Create the real fragment, home_fragment, replace the dummy fragment_container from
        //main.xml

        loadFragment(HomeFragment())

    }
    //Function to switch between fragments.
    private fun loadFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }


}




