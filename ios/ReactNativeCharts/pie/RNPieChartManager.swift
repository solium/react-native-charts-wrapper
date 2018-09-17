//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit
import React

@objc(RNPieChartManager)
@objcMembers
open class RNPieChartManager: RCTViewManager {
  var _bridge: RCTBridge? {get{return self.bridge}}

  override open func view() -> UIView! {
    let ins = RNPieChartView()
    return ins;
  }

    func resetHighlight(_ reactTag: NSNumber) {
        _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
            let view: RNPieChartView = viewRegistry![reactTag] as! RNPieChartView;
            (view.chart).highlightValue(nil)
        }
    }

    func animate(_ reactTag: NSNumber, durationMillisX: NSNumber, fromAngle: NSNumber, toAngle: NSNumber, easing: NSString) {
        _bridge?.uiManager.addUIBlock { (uiManager: RCTUIManager?, viewRegistry:[NSNumber : UIView]?) in
            let view: RNPieChartView = viewRegistry![reactTag] as! RNPieChartView;
            (view.chart).spin(duration: durationMillisX.doubleValue / 1000, fromAngle: CGFloat(fromAngle), toAngle: CGFloat(toAngle), easingOption: BridgeUtils.parseEasingOption(easing as String))
        }
    }

  override open static func requiresMainQueueSetup() -> Bool {
    return true;
  }
}
