import React from 'react';
import 'react-day-picker/lib/style.css';
import {Alert} from "reactstrap";

class CreateGrid extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      name: '',
    };
  }


  render() {
    const {grid, error} = this.props;

    if ((grid.toString()).indexOf("400") != -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">Please complete every field before continuing.</Alert></div>

        </div>
      )
    }
    else if ((grid.toString()).indexOf("409") != -1) {
      return (
        <div>
          <div className="help-block"><Alert color="danger">An Energy Grid with that name already exists.</Alert></div>

        </div>
      )
    }
    else {
      return (
        <div>
          <div className="help-block"><Alert color="success">The Energy Grid has been successfully created.</Alert></div>
        </div>
      );
    }

  }
}

export default CreateGrid;
