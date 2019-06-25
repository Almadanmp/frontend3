import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchRooms} from './Actions108';
import {Alert, Card, CardBody, Col, Collapse, Row, Table} from "reactstrap";

import TableBodyUS108 from "./TableBodyUS108"
import US105 from "../US105";

class US108Redux extends Component {
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
      {
        return (
          <div style={{float: 'left'}} className="animated fadeIn">
            <Col xs="12" sm="12" md="28">
            <Card>
              <CardBody>
                <Table responsive>
                  <TableBodyUS108 rooms={rooms}/>
                </Table>
              </CardBody>
            </Card>
            </Col>
          </div>
        );
      }
    } else {
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
)(US108Redux);
