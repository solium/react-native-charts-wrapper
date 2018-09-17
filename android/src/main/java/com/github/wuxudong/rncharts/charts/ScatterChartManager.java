package com.github.wuxudong.rncharts.charts;


import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.data.Entry;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.data.ScatterDataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;

public class ScatterChartManager extends BarLineChartBaseManager<ScatterChart, Entry> {

    public ScatterChartManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNScatterChart";
    }

    @Override
    protected ScatterChart createViewInstance(ThemedReactContext reactContext) {
        ScatterChart scatterChart = new ScatterChart(reactContext);
        scatterChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(scatterChart));
        scatterChart.setOnChartGestureListener(new RNOnChartGestureListener(scatterChart));
        return scatterChart;
    }


    @Override
    DataExtract getDataExtract() {
        return new ScatterDataExtract(getReactContext());
    }
}
