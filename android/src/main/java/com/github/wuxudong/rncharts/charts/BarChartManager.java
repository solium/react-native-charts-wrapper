package com.github.wuxudong.rncharts.charts;

import android.view.View;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.wuxudong.rncharts.data.BarDataExtract;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;

public class BarChartManager extends BarLineChartBaseManager<BarChart, BarEntry> {

    private ReactApplicationContext m_reactContext;

    public BarChartManager(ReactApplicationContext reactContext) {
        super();
        this.m_reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNBarChart";
    }

    @Override
    protected View createViewInstance(ThemedReactContext reactContext) {
        BarChart barChart = new BarChart(reactContext);
        barChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(barChart));
        return barChart;
    }

    @Override
    DataExtract getDataExtract() {
        return new BarDataExtract(m_reactContext);
    }

    @ReactProp(name = "drawValueAboveBar")
    public void setDrawValueAboveBar(BarChart chart, boolean enabled) {
        chart.setDrawValueAboveBar(enabled);
    }

    @ReactProp(name = "drawBarShadow")
    public void setDrawBarShadow(BarChart chart, boolean enabled) {
        chart.setDrawBarShadow(enabled);
    }
}
