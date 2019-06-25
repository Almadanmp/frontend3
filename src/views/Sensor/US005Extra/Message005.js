import React from 'react';
import 'react-day-picker/lib/style.css';
import {Alert} from "reactstrap";

class Message005 extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: '',
    };
  }

  render() {
    const {listSensorTypes, error} = this.props;

    if (listSensorTypes.toString().indexOf("422") !== -1 || listSensorTypes.toString().indexOf("400") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">Please complete every field before continuing.</Alert></div>

        </div>
      )
    } else if (listSensorTypes.toString().indexOf("409") !== -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">A sensor type with that name already exists.</Alert></div>

        </div>
      )
    } else {
      return (
        <div className="help-block"><Alert color="success">The sensor type has been successfully created.</Alert>
        </div>
      );
    }
  }
  }

export default Message005;
