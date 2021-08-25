package com.example.screenaddiction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels


class CalendarFragment : Fragment(R.layout.fragment_calendar) {

    private val vm: CounterViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val highestCounterView = view.findViewById<TextView>(R.id.highestCounter)
        val highestDateView = view.findViewById<TextView>(R.id.highestDate)
        val lowestCounterView = view.findViewById<TextView>(R.id.lowestCounter)
        val lowestDateView = view.findViewById<TextView>(R.id.lowestDate)
        val averageCounterView = view.findViewById<TextView>(R.id.averageCounter)


        vm.highestCounter.observe(viewLifecycleOwner) {
            highestDateView.text = it.date
            highestCounterView.text = it.value.toString()
        }

        vm.averageCounter.observe(viewLifecycleOwner) {
            averageCounterView.text = it.toString()
        }

        vm.lowestCounter.observe(viewLifecycleOwner) {
            lowestDateView.text = it.date
            lowestCounterView.text = it.value.toString()
        }


/*

            .text = context?.let { dataBase.getHighestEntry(it)
            .first }

        .text = context?.let { dataBase.getLowestEntry(it)
            .values.toString() }

        .text = context?.let { dataBase.getLowestEntry(it)
            .keys.toString() }

        .text = context?.let { dataBase.getAverageEntry(it)
            .toString() }
        }
*/
    }
}





//https://www.youtube.com/watch?v=tPV8xA7m-iw