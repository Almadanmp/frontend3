import React, {Component} from 'react';
import RoomCreator from "./US105/RoomCreator"
import {
  Collapse,
  Button,
  CardBody,
  Card,
} from 'reactstrap';
import Col from "reactstrap/es/Col";

class US105 extends Component {
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
        <Button onClick={this.toggle} className={"btn-pill"} style={{backgroundColor: '#93c4c4', marginBottom: '1rem'}}><i
          className="fa fa-plus-square-o fa-lg"/> Add a new room to the
          house</Button>
        <Collapse isOpen={this.state.collapse}>
          <Col xs="20" sm="4055555" md="4055555">
          <Card className="US105">
            <CardBody>
              <RoomCreator/>
            </CardBody>
          </Card>
          </Col>
        </Collapse>
      </div>
    )
  }
}

export default US105;
