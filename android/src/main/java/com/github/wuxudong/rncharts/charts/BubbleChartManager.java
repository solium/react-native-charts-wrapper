package com.github.wuxudong.rncharts.charts;


import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.wuxudong.rncharts.data.BubbleDataExtract;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;

public class BubbleChartManager extends ChartBaseManager<BubbleChart, BubbleEntry> {

    private ReactApplicationContext m_reactContext;

    public BubbleChartManager(ReactApplicationContext reactContext) {
        super(reactContext);
        this.m_reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNBubbleChart";
    }

    @Override
    protected BubbleChart createViewInstance(ThemedReactContext reactContext) {
        BubbleChart bubbleChart =  new BubbleChart(reactContext);
        bubbleChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(bubbleChart));
        return bubbleChart;
    }


    @Override
    DataExtract getDataExtract() {
        return new BubbleDataExtract(m_reactContext);
    }
}
