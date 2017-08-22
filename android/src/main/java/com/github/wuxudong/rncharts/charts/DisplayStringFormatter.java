package com.github.wuxudong.rncharts.charts;

import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

/**
 * Created by ali.waseem on 2017-08-22.
 */

public class DisplayStringFormatter implements IValueFormatter {
    private DataSet mdataSet;
    private List<String> mdisplayValues;

    public DisplayStringFormatter(DataSet dataSet, List<String> displayValues) {
        this.mdataSet = dataSet;
        this.mdisplayValues = displayValues;
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mdisplayValues.get(mdataSet.getEntryIndex(entry));
    }
}
