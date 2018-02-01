//  Created by xudong wu on 24/02/2017.
//  Copyright wuxudong
//

import Charts
import SwiftyJSON
import React

class RNPieChartView: RNChartViewBase {
    let _chart: PieChartView;
    let _dataExtract : PieDataExtract;

    override var chart: PieChartView {
        return _chart
    }

    override var dataExtract: DataExtract {
        return _dataExtract
    }
  
    override init(frame: CoreGraphics.CGRect) {

        self._chart = PieChartView(frame: frame)
        self._dataExtract = PieDataExtract()

        super.init(frame: frame)

        self._chart.delegate = self
        self.addSubview(_chart)

    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setDrawSliceText(_ enabled: Bool) {
        chart.drawEntryLabelsEnabled = enabled
    }


    func setUsePercentValues(_ enabled: Bool) {
        chart.usePercentValuesEnabled = enabled
    }


    func setCenterText(_ text: String) {
        chart.centerText = text
    }

    func setStyledCenterText(_ data: NSDictionary) {
        let json = BridgeUtils.toJson(data)

        var attrString: NSMutableAttributedString?
        if json["text"].string == nil
        {
            attrString = nil
        }
        else
        {
            #if os(OSX)
                let paragraphStyle = NSParagraphStyle.default().mutableCopy() as! NSMutableParagraphStyle
            #else
                let paragraphStyle = NSParagraphStyle.default.mutableCopy() as! NSMutableParagraphStyle
            #endif
            paragraphStyle.lineBreakMode = NSLineBreakMode.byTruncatingTail
            paragraphStyle.alignment = .center


            var color : NSUIColor?
            if json["color"].int != nil {
                color = RCTConvert.uiColor(json["color"].intValue)
            } else {
                color = UIColor.black
            }

            let fontSize = json["size"].number != nil ? CGFloat(json["size"].numberValue) : CGFloat(12)

            attrString = NSMutableAttributedString(string: json["text"].stringValue)
            attrString?.setAttributes([
                NSForegroundColorAttributeName: color!,
                NSFontAttributeName: NSUIFont.systemFont(ofSize: fontSize),
                NSParagraphStyleAttributeName: paragraphStyle
                ], range: NSMakeRange(0, attrString!.length))
        }

        chart.centerAttributedText = attrString

    }

    func setCenterTextRadiusPercent(_ radiusPercent: NSNumber) {
        chart.centerTextRadiusPercent = CGFloat(radiusPercent) / 100.0
    }


    func setHoleRadius(_ percent: NSNumber) {
        chart.holeRadiusPercent = CGFloat(percent) / 100.0
    }


    func setHoleColor(_ color: Int) {
        chart.holeColor = RCTConvert.uiColor(color)
    }


    func setTransparentCircleRadius(_ percent: NSNumber) {
        chart.transparentCircleRadiusPercent = CGFloat(percent) / 100.0
    }

    func setTransparentCircleColor(_ color: Int) {
        chart.transparentCircleColor = RCTConvert.uiColor(color)
    }

    func setEntryLabelColor(_ color: Int) {
        chart.entryLabelColor = RCTConvert.uiColor(color)
    }

    func setEntryLabelTextSize(_ size: NSNumber) {
        chart.entryLabelFont = chart.entryLabelFont?.withSize(CGFloat(size))
    }
    
    func setDrawEntryLabels(_ enabled: Bool) {
        chart.drawEntryLabelsEnabled = enabled
    }

    func setMaxAngle(_ maxAngle: NSNumber) {
        chart.maxAngle = CGFloat(maxAngle)
    }

    func setMinOffset(_ minOffset: NSNumber) {
        chart.minOffset = CGFloat(minOffset)
    }

    func setRotationEnabled(_ enabled: Bool) {
        chart.rotationEnabled = enabled
    }

    func setMinOffset(_ minOffset: NSNumber) {
        chart.minOffset = CGFloat(minOffset)
    }

    func setSpin(_ spin: NSDictionary) {
        let config = BridgeUtils.toJson(spin)
        var duration: Double = 0.0;
        var fromAngle: CGFloat = 0.0;
        var toAngle: CGFloat = 0.0;
        var easing: ChartEasingOption = .linear;

        if let durationNumber = config["durationMilli"].number {
            duration = Double(durationNumber) / 1000
        }
        if let fromAngleNumber = config["fromAngle"].number {
            fromAngle = CGFloat(fromAngleNumber)
        }
        if let toAngleNumber = config["toAngle"].number {
            toAngle = CGFloat(toAngleNumber)
        }
        if let easingString = config["easing"].string {
            easing = BridgeUtils.parseEasingOption(easingString)
        }

        chart.spin(duration: duration, fromAngle: fromAngle, toAngle: toAngle, easingOption: easing)
    }

    func setRotationAngle(_ angle: NSNumber) {
        chart.rotationAngle = CGFloat(angle)
    }

    override func didSetProps(_ changedProps: [String]!) {
        let pieChartDataSet = chart.data?.dataSets[0] as? PieChartDataSet

        pieChartDataSet?.entryLabelColor = chart.entryLabelColor
        pieChartDataSet?.entryLabelFont = chart.entryLabelFont
    }
}
