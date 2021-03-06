import PropTypes from 'prop-types';
import React, {Component} from 'react';
import {
  requireNativeComponent,
  View,
  UIManager,
  findNodeHandle
} from 'react-native';

import PieRadarChartBase from './PieRadarChartBase';
import {pieData} from './ChartDataConfig';

class PieChart extends React.Component {
  getNativeComponentName() {
    return 'RNPieChart'
  }

  resetHighlight() {
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this),
      UIManager.RNPieChart.Commands.resetHighlight,
      []
    )
  }

  animate(durationMilli, fromAngle, toAngle, easing) {
    UIManager.dispatchViewManagerCommand(
      findNodeHandle(this),
      UIManager.RNPieChart.Commands.animate,
      [durationMilli, fromAngle, toAngle, easing]
    )
  }

  getNativeComponentRef() {
    return this.nativeComponentRef
  }

  render() {
    return <RNPieChart {...this.props} ref={ref => this.nativeComponentRef = ref} />;
  }
}

PieChart.propTypes = {
  ...PieRadarChartBase.propTypes,

  drawEntryLabels: PropTypes.bool,
  usePercentValues: PropTypes.bool,

  centerText: PropTypes.string,
  styledCenterText: PropTypes.shape({
    text: PropTypes.string,
    color: PropTypes.number,
    size: PropTypes.number
  }),
  centerTextRadiusPercent: PropTypes.number,
  holeRadius: PropTypes.number,
  holeColor: PropTypes.number,
  transparentCircleRadius: PropTypes.number,
  transparentCircleColor: PropTypes.number,

  entryLabelColor: PropTypes.number,
  entryLabelTextSize: PropTypes.number,
  maxAngle: PropTypes.number,

  minOffset: PropTypes.number,

  // TODO PieChart should have only one dataset
  data: pieData,
  rotationAngle: PropTypes.number,
  spin: PropTypes.shape({
    durationMilli: PropTypes.number,
    fromAngle: PropTypes.number,
    toAngle: PropTypes.number,
    easing: PropTypes.string
  }),
  useCustomHighLightColor: PropTypes.bool,
};

var RNPieChart = requireNativeComponent('RNPieChart', PieChart, {
  nativeOnly: {onSelect: true, onChange: true}
});

export default PieChart
