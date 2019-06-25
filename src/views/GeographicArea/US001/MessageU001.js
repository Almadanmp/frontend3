import React from 'react';
import 'react-day-picker/lib/style.css';
import {Alert} from "reactstrap";
import {connect} from 'react-redux';

class MessageU001 extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: ''
    };
  }

  render() {
    const {addedSensor, name} = this.props;
    console.log(addedSensor)

    if ((addedSensor.toString()).indexOf("400") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">Please complete the field before continuing.</Alert></div>
        </div>
      )
    } else if ((addedSensor.toString()).indexOf("409") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">An area type named '{name}' already exists.</Alert></div>
        </div>
      )
    } else {
      return (
        <div className="help-block"><Alert color="success">
          <p>The area type '{name}' was successfully added.</p>
        </Alert></div>
      );
    }
  }
}

export default MessageU001;
