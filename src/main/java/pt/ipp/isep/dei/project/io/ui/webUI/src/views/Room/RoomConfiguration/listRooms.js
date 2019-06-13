import React, {Component} from 'react';
import {
  Card,
  CardBody,
  Col,
  Form,
  FormGroup,
  Input,
  Label,
  Table,
  Row,
  ListGroup,
  ListGroupItem,
  ListGroupItemHeading,
  ListGroupItemText,
  CardHeader,
  Badge,
  TabContent, TabPane
} from "reactstrap";
import US250GetSensors from "./US250/US250GetSensors";
import {fetchRooms} from "../../House/HouseConfiguration/US108/Actions108";
import connect from "react-redux/es/connect/connect";


class ListRooms extends Component {

  constructor(props) {
    super(props);
    this.state = {
      activeTab: 1
    }
  }


  toggle(tab) {
    if (this.state.activeTab !== tab) {
      this.setState({
        activeTab: tab
      });
    }
  }

  componentDidMount() {
    this.props.onFetchRooms();
  }

  render() {

    const {loading, rooms, error} = this.props;
    if (loading == true) {
      return <div>Loading...</div>
    } else {
      return (
        <>


          <Row>
            <Col>
              <Card>
                <CardHeader>
                  <i className="fa fa-align-justify"></i><strong>Room Sensors</strong>
                  <small>by room</small>
                  <div className="card-header-actions">
                  </div>
                </CardHeader>
                <CardBody>

                  {rooms.map(item => (
                    <Row>
                      <Col xs="4">
                        <ListGroup id="list-tab" role="tablist">
                          <ListGroupItem onClick={() => this.toggle(item.name)} action
                                         active={this.state.activeTab === item.name}>{item.name}</ListGroupItem>
                        </ListGroup>
                      </Col>


                        <TabContent activeTab={this.state.activeTab}>
                          <TabPane tabId={item.name} >

                        <US250GetSensors roomID={item.name}/>
                          </TabPane>
                        </TabContent>

                    </Row>
                  ))}
                </CardBody>
              </Card>
            </Col>
          </Row>


        </>
      );
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
    onFetchRooms: () => {
      dispatch(fetchRooms())
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ListRooms);