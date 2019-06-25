import React from 'react';
import 'react-day-picker/lib/style.css';
import {Alert} from "reactstrap";

class CreateArea extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: ''
    };
  }

  render() {
    const {areas} = this.props;
    const {name, typeArea, length, width, latitude, longitude, altitude} = this.props;

    if ((areas.toString()).indexOf("400") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">
            Please complete every field before continuing.
          </Alert></div>
        </div>
      )
    }
    else if ((areas.toString()).indexOf("422") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">The parameters length and width need to be greater than zero.
          </Alert></div>
        </div>
      )
    }
    else if ((areas.toString()).indexOf("409") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">That geographic area already exists in the system.
          </Alert></div>
        </div>
      )
    }
    else {
      return (
        <div className="help-block"><Alert color="success">
          <p>The following geographic area was added:</p>
          <ul>
            <li>
              Name: {name}
            </li>
            <li>
              Type: {typeArea}
            </li>
            <li>
              Area Length: {length} Width: {width}
            </li>
            <li>
              Latitude: {latitude} Longitude: {longitude} Altitude: {altitude}
            </li>
          </ul>
        </Alert></div>
      );
    }

  }
}


export default CreateArea;
