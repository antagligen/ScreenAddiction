package com.example.screenaddiction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomnavigation.BottomNavigationView


class CalendarFragment : Fragment(R.layout.fragment_calendar) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataBase = DatabaseHandles()

        view.findViewById<TextView>(R.id.highestCounter).text = context?.let { dataBase.getHighestEntry(it)
            .values.toString() }

        view.findViewById<TextView>(R.id.highestDate).text = context?.let { dataBase.getHighestEntry(it)
            .keys.toString() }

        view.findViewById<TextView>(R.id.lowestCounter).text = context?.let { dataBase.getLowestEntry(it)
            .values.toString() }

        view.findViewById<TextView>(R.id.lowestDate).text = context?.let { dataBase.getLowestEntry(it)
            .keys.toString() }

        view.findViewById<TextView>(R.id.averageCounter).text = context?.let { dataBase.getAverageEntry(it)
            .toString() }
        }

    }





//https://www.youtube.com/watch?v=tPV8xA7m-iw