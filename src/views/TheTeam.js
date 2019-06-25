import React, {Component} from 'react';
import {Button, Col, Container, Input, InputGroup, InputGroupAddon, InputGroupText, Row} from "reactstrap";

class TheTeam extends Component {
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
        <Container>
          <Row className="justify-content-start">
            <Col md="15">
              <h2 className="pt-12">The dev team!</h2>
              <p>Meet everyone involved in the making of the SmartHome Web app.</p></Col></Row>
          <Row>
            <Col style={{textAlign: "center"}}>
              <img src="https://imgur.com/ICquRJa.png" width="40%" height="60%"/>
              <h3>André</h3>
              <small>1181689@isep.ipp.pt</small>
              <p>Civil Engineer</p>
            </Col>
            <Col style={{textAlign: "center"}}>
              <img src="https://imgur.com/MIORjL7.png" width="40%" height="60%"/>
              <h3>Cárina</h3>
              <small>1181695@isep.ipp.pt</small>
              <p>Biologist</p>
            </Col>
            <Col style={{textAlign: "center"}}>
              <img src="https://imgur.com/a78ZEfT.png" width="40%" height="60%"/>
              <h3>Daniel</h3>
              <small>1181699@isep.ipp.pt</small>
              <p>Pharmacist</p>
            </Col>
          </Row>
          <Row>
            <Col style={{textAlign: "center"}}>
              <img src="https://imgur.com/7yG7o0m.png" width="40%" height="60%"/>
              <h3>Daniela</h3>
              <small>1181701@isep.ipp.pt</small>
              <p>Forensic Psychologist</p>
            </Col>
            <Col style={{textAlign: "center"}}>
              <img src="https://imgur.com/O0UJCta.png" width="40%" height="60%"/>
              <h3>João</h3>
              <small>1181711@isep.ipp.pt</small>
              <p>Philosopher</p>
            </Col>
            <Col style={{textAlign: "center"}}>
              <img src="https://imgur.com/VzeQmTf.png" width="40%" height="60%"/>
              <h3>Maria</h3>
              <small>1181716@isep.ipp.pt</small>
              <p>Script writer / Producer</p>
              </Col>
          </Row>
          <Row>
            <Col style={{textAlign: "center"}}>
              <img src="https://imgur.com/5bkoNJE.png" width="40%" height="60%"/>
              <h3>Miguel</h3>
              <small>1181718@isep.ipp.pt</small>
              <p>Management</p>
            </Col>
            <Col style={{textAlign: "center"}}>
              <img src="https://imgur.com/ezHuUEn.png" width="40%" height="60%"/>
              <h3>Nuno</h3>
              <small>1181722@isep.ipp.pt</small>
              <p>Dentist</p>
            </Col>
            <Col style={{textAlign: "center"}}>
              <img src="https://imgur.com/GTWerXw.png" width="40%" height="60%"/>
              <h3>Teresa</h3>
              <small>1181730@isep.ipp.pt</small>
            <p>Pharmacist</p>
            </Col>
          </Row>
        </Container>

      </div>
    );
  }
}

export default TheTeam;

