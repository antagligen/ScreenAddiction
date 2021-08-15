package com.example.screenaddiction

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.data.LineData





class ChartFragment : Fragment(R.layout.fragment_chart) {

    private val dataBase = DatabaseHandles()

    val BARWIDTH = 0.8f
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // createBarChart(view)
        //Code below is latest thing changed.
        createLineChartWeek(this.requireContext(), view)

    }
    private fun createLineChartWeek(context: Context, view: View) {

        val hMap = dataBase.getAllEntries(context)

        val lineChart = view.findViewById<com.github.mikephil.charting.charts.LineChart>(R.id.lineChart)

        val entries = createDataEntries(hMap)
        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 2f))

        val xAxisLabels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" )

        lineChart.xAxis.valueFormatter =  IndexAxisValueFormatter(xAxisLabels)
        //add the values to the dataset
        val lDataSet = LineDataSet(entries,"dataset")
        lDataSet.setDrawValues(false)
        lDataSet.setDrawFilled(false)
        lDataSet.lineWidth = 3f
        lDataSet.fillColor = R.color.black
        lDataSet.fillAlpha = R.color.purple_700




        //AXIS
        lineChart.xAxis.labelRotationAngle = 0f
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.xAxis.setDrawAxisLine(false)
        lineChart.legend.isEnabled = false
        lineChart.xAxis.axisMaximum = 7f
        lineChart.xAxis.axisMinimum = 0f
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)



        lineChart.axisRight.isEnabled = false
        lineChart.xAxis.axisMaximum = 0.1f


//Part10
        lineChart.animateX(1800, Easing.EaseInExpo)

        lineChart.data = LineData(lDataSet)
        lineChart.invalidate() // refresh

    }

    private fun fetchLineChartData() : MutableMap<String, Any?>{

        val hMap: MutableMap<String,Any?> = LinkedHashMap()
        context?.getSharedPreferences(DATABASE, Context.MODE_PRIVATE)?.all?.forEach {
            hMap[it.key] = it.value
        }
        return hMap
    }
    private fun createDataEntries(hMap : MutableMap<String, Any?>) : ArrayList<Entry>{
        var xAxis = 0f
        val entries = ArrayList<Entry>()
        for ((key, value) in hMap ){
            entries.add(Entry(xAxis,value.toString().toFloat()))
            xAxis =+ 1f
        }
        return entries
    }




    /*
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
*/

}

