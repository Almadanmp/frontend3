import React, {Component} from 'react';
import {Card, CardBody, Col, Table, Row, CardHeader, Alert, Container} from "reactstrap";
import US605GetCurrentTemperature from "./US605GetCurrentTemperature";
import {fetchRooms} from "../../../House/HouseConfiguration/US108/Actions108";
import connect from "react-redux/es/connect/connect";

class US605 extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.onFetchUsers();
  }

  render() {
    const {loading, rooms} = this.props;
    if (loading === true) {
      return (
        <div className="spinner-border" role="status">
          <span className="sr-only"> Loading...</span>
        </div>
      );
    }
    if (rooms.length > 0) {
      return (
        <>
          <Row>
            <Col md="15">
              <Card>
                <CardHeader>
                  Current Temperature
                </CardHeader>
                <CardBody>
                  <Row className="justify-content-start">
                    <Col md="9">
                  <Table responsive>
                    <CardBody>
                      <tr>
                        <th>Room</th>
                        <th style={{textAlign:"center"}}>Temperature</th>
                      </tr>
                      {rooms.map(items => (
                        <tr>
                          <td value={items.name} key={items.name}> {items.name} </td>
                          <td style={{textAlign:"center"}}><US605GetCurrentTemperature
                            link={items.links.find((hrefs) => hrefs.rel === 'Get Room Temperature')} roomID={items.name}/>
                          </td>
                        </tr>
                      ))}
                    </CardBody>
                  </Table>
                    </Col>
                    <Col style={{textAlign: "right"}}>
                      <img src={"https://imgur.com/QHIi5EA.png"} width="100%" height="11%" style={{display: "inline-block"}}/>
                      <p></p>
                    </Col>
                  </Row>
                </CardBody>
              </Card>
            </Col>
          </Row>
        </>
      );
    }
    else {
      return (
        <div className="help-block"><Alert color="warning">No rooms on the house</Alert></div>
      )
    }
  }
}

const mapStateToProps = (state) => {
  return {
    loading: state.Reducer108.loading,
    rooms: state.Reducer108.rooms,
    error: state.Reducer108.error
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchUsers: () => {
      dispatch(fetchRooms())
    }

  }
};
export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US605);
