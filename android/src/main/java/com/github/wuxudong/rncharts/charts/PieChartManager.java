package com.github.wuxudong.rncharts.charts;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.github.wuxudong.rncharts.data.DataExtract;
import com.github.wuxudong.rncharts.data.PieDataExtract;
import com.github.wuxudong.rncharts.listener.RNOnChartGestureListener;
import com.github.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;
import com.github.wuxudong.rncharts.utils.BridgeUtils;

import java.util.Map;

import javax.annotation.Nullable;

public class PieChartManager extends ChartBaseManager<PieChart, PieEntry> {

    public PieChartManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNPieChart";
    }

    @Override
    protected PieChart createViewInstance(ThemedReactContext reactContext) {
        PieChart pieChart =  new PieChart(reactContext);
        pieChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(pieChart));
        pieChart.setOnChartGestureListener(new RNOnChartGestureListener(pieChart));
        return pieChart;
    }

    @Override
    DataExtract getDataExtract() {
        return new PieDataExtract(getReactContext());
    }

    @ReactProp(name = "drawEntryLabels")
    public void setDrawEntryLabels(PieChart chart, boolean enabled) {
        chart.setDrawEntryLabels(enabled);
    }

    @ReactProp(name = "usePercentValues")
    public void setUsePercentValues(PieChart chart, boolean enabled) {
        chart.setUsePercentValues(enabled);

    }

    @ReactProp(name = "centerText")
    public void setCenterText(PieChart chart, String text) {
        chart.setCenterText(text);
    }

    @ReactProp(name = "styledCenterText")
    public void setStyledCenterText(PieChart chart, ReadableMap propMap) {
        if (BridgeUtils.validate(propMap, ReadableType.String, "text")) {
            chart.setCenterText(propMap.getString("text"));
        } else {
            chart.setCenterText("");
        }

        if (BridgeUtils.validate(propMap, ReadableType.Number, "color")) {
            chart.setCenterTextColor(propMap.getInt("color"));
        }

        if (BridgeUtils.validate(propMap, ReadableType.Number, "size")) {
            chart.setCenterTextSize((float) propMap.getDouble("size"));
        }
    }

    @ReactProp(name = "centerTextRadiusPercent")
    public void setCenterTextRadiusPercent(PieChart chart, float radiusPercent) {
        chart.setCenterTextRadiusPercent(radiusPercent);
    }

    @ReactProp(name = "holeRadius")
    public void setHoleRadius(PieChart chart, float percent) {
        chart.setHoleRadius(percent);
    }

    @ReactProp(name = "holeColor")
    public void setHoleColor(PieChart chart, Integer color) {
        chart.setHoleColor(color);
    }

    @ReactProp(name = "transparentCircleRadius")
    public void setTransparentCircleRadius(PieChart chart, float percent) {
        chart.setTransparentCircleRadius(percent);
    }

    @ReactProp(name = "transparentCircleColor")
    public void setTransparentCircleColor(PieChart chart, Integer color) {
        chart.setTransparentCircleColor(color);
    }

    @ReactProp(name = "entryLabelColor")
    public void setEntryLabelColor(PieChart chart, Integer color) {
        chart.setEntryLabelColor(color);
    }

    @ReactProp(name = "entryLabelTextSize")
    public void setEntryLabelTextSize(PieChart chart, float size) {
        chart.setEntryLabelTextSize(size);
    }

    @ReactProp(name = "maxAngle")
    public void setMaxAngle(PieChart chart, float maxAngle) {
        chart.setMaxAngle(maxAngle);
    }

    @ReactProp(name = "rotationEnabled")
    public void setRotationEnabled(PieChart chart, boolean enabled) {
        chart.setRotationEnabled(enabled);
    }

    @ReactProp(name = "rotationAngle")
    public void setRotationAngle(PieChart chart, float angle) {
        chart.setRotationAngle(angle);
    }

    @ReactProp(name = "spin")
    public void setSpinAngleDuraction(PieChart chart, ReadableMap propMap) {
        int durationMilli = 0;
        float fromAngle = 0f;
        float toAngle = 0f;
        Easing.EasingOption easing = Easing.EasingOption.Linear;
        if (BridgeUtils.validate(propMap, ReadableType.Number, "durationMilli")) {
            durationMilli = propMap.getInt("durationMilli");
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "fromAngle")) {
            fromAngle = (float) propMap.getDouble("fromAngle");
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "toAngle")) {
            toAngle = (float) propMap.getDouble("toAngle");
        }
        if (BridgeUtils.validate(propMap, ReadableType.Number, "easing")) {
            easing = Easing.EasingOption.valueOf(propMap.getString("easing"));
        }
        chart.spin(durationMilli, fromAngle, toAngle, easing);
    }

    @ReactProp(name = "minOffset")
    public void setMinOffSet(PieChart chart, float offset) {
        chart.setMinOffset(offset);
    }

    @ReactProp(name = "useCustomHighLightColor")
    public void setUseCustomHighLightColor(PieChart chart, boolean enabled) {
        chart.setUseCustomHighLightColor(enabled);
    }

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("animate", ANIMATE, "resetHighlight", RESET_HIGHLIGHT);
    }

    @Override
    public void receiveCommand(PieChart root, int commandId, @Nullable ReadableArray args) {
        Assertions.assertNotNull(root);
        Assertions.assertNotNull(args);
        switch (commandId) {
            case ANIMATE: {
                root.spin(args.getInt(0), (float) args.getDouble(1), (float) args.getDouble(2), Easing.EasingOption.valueOf(args.getString(3)));
                return;
            }
            case RESET_HIGHLIGHT: {
                root.highlightValue(null, false);
                return;
            }
        }
    }

}
