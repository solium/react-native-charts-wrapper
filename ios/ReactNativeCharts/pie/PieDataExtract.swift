//  Created by xudong wu on 02/03/2017.
//  Copyright © 2017 wuxudong. All rights reserved.
//

import Foundation

import SwiftyJSON
import Charts

class PieDataExtract : DataExtract {
    override func createData() -> ChartData {
        return PieChartData();
    }
    
    override func createDataSet(_ entries: [ChartDataEntry]?, label: String?) -> IChartDataSet {
        return PieChartDataSet(values: entries, label: label)
    }
    
    override func dataSetConfig(_ dataSet: IChartDataSet, config: JSON) {
        let pieDataSet = dataSet as! PieChartDataSet;
        
        ChartDataSetConfigUtils.commonConfig(pieDataSet, config: config);
        
        // PieDataSet only config
        if config["sliceSpace"].float != nil {
            pieDataSet.sliceSpace = CGFloat(config["sliceSpace"].floatValue)
        }
        
        if config["selectionShift"].float != nil {
            pieDataSet.selectionShift = CGFloat(config["selectionShift"].floatValue)
        }

        if let xValuePosition = config["xValuePosition"].string {
            pieDataSet.xValuePosition = BridgeUtils.parseValuePositionPieDataSet(xValuePosition)
        }

        if let yValuePosition = config["yValuePosition"].string {
            pieDataSet.yValuePosition = BridgeUtils.parseValuePositionPieDataSet(yValuePosition)
        }

        if let valueLinePart1OffsetPercentage = config["valueLinePart1OffsetPercentage"].number {
            pieDataSet.valueLinePart1OffsetPercentage = CGFloat(valueLinePart1OffsetPercentage) / 100
        }

        if let valueLinePart1Length = config["valueLinePart1Length"].number {
            pieDataSet.valueLinePart1Length = CGFloat(valueLinePart1Length)
        }

        if let valueLinePart2Length = config["valueLinePart2Length"].number {
            pieDataSet.valueLinePart2Length = CGFloat(valueLinePart2Length)
        }

        if let valueLineVariableLength = config["valueLineVariableLength"].bool {
            pieDataSet.valueLineVariableLength = valueLineVariableLength
        }

    }
    
    override func createEntry(_ values: [JSON], index: Int) -> ChartDataEntry {
        var entry: PieChartDataEntry;
        
        let item = values[index];
        
        if item.dictionary != nil {
            let dict = item;
            
            var value : Double;
            if dict["value"].double != nil {
                value = Double((dict["value"].doubleValue));
            } else {
                fatalError("invalid data " + values.description);
            }
            
            if dict["label"].string != nil {
                entry = PieChartDataEntry(value: value, label: dict["label"].stringValue)
            } else {
                entry = PieChartDataEntry(value: value)
            }
            
            entry.data = dict as AnyObject?
        } else if item.double != nil {
            entry = PieChartDataEntry(value : item.doubleValue);
        } else {
            fatalError("invalid data " + values.description);
        }
        
        return entry;
    }
}
