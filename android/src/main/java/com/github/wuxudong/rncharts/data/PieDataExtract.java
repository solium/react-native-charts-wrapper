package com.github.wuxudong.rncharts.data;

import android.graphics.Typeface;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.wuxudong.rncharts.utils.BridgeUtils;
import com.github.wuxudong.rncharts.utils.ChartDataSetConfigUtils;
import com.github.wuxudong.rncharts.utils.ConversionUtil;

import java.util.ArrayList;

/**
 * Created by xudong on 02/03/2017.
 */

public class PieDataExtract extends DataExtract<PieData, PieEntry> {

    public PieDataExtract(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    PieData createData() {
        return new PieData();
    }

    @Override
    IDataSet<PieEntry> createDataSet(ArrayList<PieEntry> entries, String label) {
        return new PieDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<PieEntry> dataSet, ReadableMap config) {
        PieDataSet pieDataSet = (PieDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(pieDataSet, config, getReactContext());

        // PieDataSet only config
        if (BridgeUtils.validate(config, ReadableType.Number, "sliceSpace")) {
            pieDataSet.setSliceSpace((float) config.getDouble("sliceSpace"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "selectionShift")) {
            pieDataSet.setSelectionShift((float) config.getDouble("selectionShift"));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "xValuePosition")) {
            pieDataSet.setXValuePosition(PieDataSet.ValuePosition.valueOf(config.getString("xValuePosition").toUpperCase()));
        }
        if (BridgeUtils.validate(config, ReadableType.String, "yValuePosition")) {
            pieDataSet.setYValuePosition(PieDataSet.ValuePosition.valueOf(config.getString("yValuePosition").toUpperCase()));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "valueLinePart1OffsetPercentage")) {
            pieDataSet.setValueLinePart1OffsetPercentage((float) config.getDouble("valueLinePart1OffsetPercentage"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "valueLinePart1Length")) {
            pieDataSet.setValueLinePart1Length((float) config.getDouble("valueLinePart1Length"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "valueLinePart2Length")) {
            pieDataSet.setValueLinePart2Length((float) config.getDouble("valueLinePart2Length"));
        }
        if (BridgeUtils.validate(config, ReadableType.Boolean, "valueLineVariableLength")) {
            pieDataSet.setValueLineVariableLength(config.getBoolean("valueLineVariableLength"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "highLightColor")) {
            pieDataSet.setHighLightColor(config.getInt("highLightColor"));
        }
    }

    @Override
    PieEntry createEntry(ReadableArray values, int index) {
        PieEntry entry;

        if (ReadableType.Map.equals(values.getType(index))) {
            ReadableMap map = values.getMap(index);

            float value = (float) map.getDouble("value");
            if (BridgeUtils.validate(map, ReadableType.String, "label")) {
                entry = new PieEntry(value, map.getString("label"), ConversionUtil.toMap(map));
            } else {
                entry = new PieEntry(value, ConversionUtil.toMap(map));
            }
        } else if (ReadableType.Number.equals(values.getType(index))) {
            entry = new PieEntry((float) values.getDouble(index));
        } else {
            throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
        }

        return entry;
    }

}
