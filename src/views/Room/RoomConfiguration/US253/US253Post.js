import React from 'react';
import 'react-day-picker/lib/style.css';
import {Alert} from "reactstrap";
import {connect} from 'react-redux';

class US253Post extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: ''
    };
  }

  render() {
    const {sensor, error} = this.props;
    const {roomID, typeSensor, name, sensorId, dateStartedFunctioning} = this.props;

    if ((sensor.toString()).indexOf("422") !== -1 || (sensor.toString()).indexOf("400") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">Please complete every field before continuing.
            <br></br>
            Make sure you select the date.</Alert></div>
        </div>
      )
    }
    if ((sensor.toString()).indexOf("409") !== -1 ) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">A sensor with that ID already exists in your house.</Alert></div>
        </div>
      )
    }
    else {
      return (
        <div className="help-block"><Alert color="success">
          <p>The following sensor was added:</p>
          <ul>
            <li>
              Name: {name}, ID: {sensorId}
            </li>
            <li>
              Type: {typeSensor}
            </li>
            <li>
              Room ID: {roomID}
            </li>
            <li>
              Start Date: {dateStartedFunctioning}
            </li>
          </ul>
        </Alert></div>
      );
    }

  }
}


const mapStateToProps = (state) => {
  return {
    loading: state.Reducers253.loading,
    sensor: state.Reducers253.sensor,
    error: state.Reducers253.error
  }
};
export default connect(mapStateToProps, null)( US253Post);
