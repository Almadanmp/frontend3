import React, {Component} from 'react';
import US130 from './US130'
import RoomGrid from "./SmartGrid/RoomGrid"
import {Col, Row} from "reactstrap";

class HouseConfiguration extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.state = {collapse: false};
  }

  toggle() {
    this.setState(state => ({collapse: !state.collapse}));
  }

  render() {
    return (
      <div>
        <h2>Welcome to the Energy Grid Menu.</h2>

        <p></p>
        <RoomGrid/>
        <p></p>




      </div>
    );
  }
}

export default HouseConfiguration;
