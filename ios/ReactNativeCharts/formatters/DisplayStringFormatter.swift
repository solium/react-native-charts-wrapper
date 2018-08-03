//
//  DisplayStringFormatter.swift
//  Shareworks
//
//  Created by Ali Waseem on 2018-08-03.
//  Copyright © 2018 Solium Capital Inc. All rights reserved.
//

import Foundation
import Charts

class DisplayStringFormatter : NSObject, IValueFormatter {

    var dataSet: ChartDataSet
    var displayValues: [String]


    init(chartDataSet: ChartDataSet, chartDisplayValues: [String]) {
        self.dataSet = chartDataSet
        self.displayValues = chartDisplayValues
    }

    func stringForValue(_ value: Double, entry: ChartDataEntry, dataSetIndex: Int, viewPortHandler: ViewPortHandler?) -> String {
        return displayValues[dataSet.entryIndex(entry: entry)]
    }
}

