package com.github.wuxudong.rncharts.charts;

import android.view.View;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.wuxudong.rncharts.data.BarDataExtract;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;

import java.util.Map;

import javax.annotation.Nullable;

public class BarChartManager extends BarLineChartBaseManager<BarChart, BarEntry> {

    public BarChartManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNBarChart";
    }

    @Override
    protected BarChart createViewInstance(ThemedReactContext reactContext) {
        BarChart barChart = new BarChart(reactContext);
        barChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(barChart));
        barChart.setOnChartGestureListener(new RNOnChartGestureListener(barChart));
        return barChart;
    }

    @Override
    DataExtract getDataExtract() {
        return new BarDataExtract(getReactContext());
    }

    @ReactProp(name = "drawValueAboveBar")
    public void setDrawValueAboveBar(BarChart chart, boolean enabled) {
        chart.setDrawValueAboveBar(enabled);
    }

    @ReactProp(name = "drawBarShadow")
    public void setDrawBarShadow(BarChart chart, boolean enabled) {
        chart.setDrawBarShadow(enabled);
    }

    @ReactProp(name = "highlightFullBarEnabled")
    public void setHighlightFullBarEnabled(BarChart chart, boolean enabled) {
        chart.setHighlightFullBarEnabled(enabled);
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return super.getCommandsMap();
    }

    @Override
    public void receiveCommand(BarChart root, int commandId, @Nullable ReadableArray args) {
        super.receiveCommand(root, commandId, args);
    }
}
