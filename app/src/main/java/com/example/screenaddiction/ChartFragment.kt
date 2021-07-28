package com.example.screenaddiction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels


class ChartFragment : Fragment(R.layout.fragment_chart) {
    private val model: CounterViewModel by activityViewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}

//https://www.youtube.com/watch?v=tPV8xA7m-iw