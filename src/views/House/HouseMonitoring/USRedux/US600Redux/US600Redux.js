import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchTemp} from './Actions600';
import {CardBody, Col, Container, Row} from "reactstrap";

class US600Redux extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.onFetchTemp();
  }

  render() {
    const {loading, temp} = this.props;
    if (loading === true) {
      return (
        <div className="spinner-border" role="status">
          <span className="sr-only"> Loading...</span>
        </div>
      );
    }
    else {
      if ((temp.toString()).indexOf("ERROR") != -1) {
        return (
          <Container>
            <Row className="justify-content-start">
              <Col md="15">
              <h4  style={{textAlign: "left"}}> ERROR: No Data Available.
              </h4>
              </Col>
              <Col style={{textAlign: "right"}}>
            <img src={"https://imgur.com/kn853RM.png"} width="50%" height="75%" style={{display: "inline-block"}}/>
            <p></p>
              </Col>
            </Row>
          </Container>

        )
      }
      return (

        <Container>
          <Row className="justify-content-start">
            <Col md="13">
              <br></br>
              <br></br>
              <h4 key={temp} style={{textAlign: "left"}} >
                Current Temperature: {temp} ºC
              </h4>
            </Col>
            <Col style={{textAlign: "right"}}>
              <img src={"https://imgur.com/kn853RM.png"} width="50%" height="75%" style={{display: "inline-block"}}/>
              <p></p>
            </Col>
          </Row>
        </Container>
      );

    }
  }
}

const mapStateToProps = (state) => {
  return {
    loading: state.Reducers600.loading,
    temp: state.Reducers600.temp,
    error: state.Reducers600.error
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchTemp: () => {
      dispatch(fetchTemp())
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US600Redux);
