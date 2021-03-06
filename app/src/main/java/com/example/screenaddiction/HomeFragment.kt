package com.example.screenaddiction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.fragment.app.activityViewModels


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val model: CounterViewModel by activityViewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Observe the counter in the viewmodel and update the GUI with the value.
        model.totalCounter.observe(viewLifecycleOwner) {
          view.findViewById<TextView>(R.id.uiScreenCounter).text = it.toString()
        }
    }



}

//https://www.youtube.com/watch?v=tPV8xA7m-iw