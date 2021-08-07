package com.example.screenaddiction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate


class ChartFragment : Fragment(R.layout.fragment_chart) {
    private val model: CounterViewModel by activityViewModels()



    val BARWIDTH = 0.8f
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createBarChart(view)



    }
    private fun createBarChart(view: View){

        //Creates a list with the bars, each entry represents a a bar on x and y-axis.
        val entries: ArrayList<BarEntry> = ArrayList()
        entries.add(BarEntry(1f, 4f))
        entries.add(BarEntry(2f, 10f))
        entries.add(BarEntry(3f, 2f))
        entries.add(BarEntry(4f, 15f))
        entries.add(BarEntry(5f, 13f))
        entries.add(BarEntry(6f, 2f))

        val labels = ArrayList<String>()
        labels.add("18-Jan")
        labels.add("19-Jan")
        labels.add("20-Jan")
        labels.add("21-Jan")
        labels.add("22-Jan")
        labels.add("23-Jan")


        //Takes all the "bars" or data and represents them together.
        val barDataSet = BarDataSet(entries, "BarDataSet")

        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)
        data.barWidth = BARWIDTH
        val barChart = view.findViewById<com.github.mikephil.charting.charts.BarChart>(R.id.barChart)
        barChart.data = data



        //hide grid lines
        barChart.axisLeft.setDrawGridLines(false)
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.setFitBars(true)

        //remove right y-axis
        barChart.axisRight.isEnabled = false


        //remove legend
        barChart.legend.isEnabled = false


        //add animation
        barChart.animateY(3000)


        //draw chart
        barChart.invalidate()
    }


}

