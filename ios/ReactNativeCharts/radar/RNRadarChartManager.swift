//  Created by xudong wu on 23/02/2017.
//  Copyright wuxudong
//

import UIKit
import React

@objc(RNRadarChartManager)
open class RNRadarChartManager: RCTViewManager {
  override open func view() -> UIView! {
    let ins = RNRadarChartView()
    return ins;
  }

  override open static func requiresMainQueueSetup() -> Bool {
    return true;
  }

}
