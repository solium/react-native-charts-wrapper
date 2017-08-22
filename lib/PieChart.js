import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import PieRadarChartBase from './PieRadarChartBase';
import {pieData} from './ChartDataConfig';

const iface = {
  name: 'PieChart',
  propTypes: {
    ...PieRadarChartBase.propTypes,

    drawEntryLabels: PropTypes.bool,
    usePercentValues: PropTypes.bool,

    centerText: PropTypes.string,
    centerTextRadiusPercent: PropTypes.number,
    holeRadius: PropTypes.number,
    holeColor: PropTypes.number,
    transparentCircleRadius: PropTypes.number,
    transparentCircleColor: PropTypes.number,

    entryLabelColor: PropTypes.number,
    entryLabelTextSize: PropTypes.number,
    maxAngle: PropTypes.number,
    rotationAngle: PropTypes.number,
    spin: PropTypes.shape({
      durationMilli: PropTypes.number,
      fromAngle: PropTypes.number,
      toAngle: PropTypes.number,
      easing: PropTypes.string
    }),
    // TODO PieChart should have only one dataset
    data: pieData
  }
};

export default requireNativeComponent('RNPieChart', iface, {
  nativeOnly: { onSelect: true }
});
