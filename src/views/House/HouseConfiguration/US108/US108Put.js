import React, {Component} from 'react';
import {Alert} from "reactstrap";

class US108Put extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
    }
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    const name = this.props.name;
    const floor = this.props.floor;
    const width = this.props.width;
    const length = this.props.length;
    const height = this.props.height;
    const link = this.props.link.href;
    console.log(this.props);
    fetch(link, {
      method: 'put',
      headers: {
        'Authorization': token,
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json"
      },
      body: JSON.stringify({name, floor, width, length, height})
    })
      .then(res => {
        if (res.status === 422) {
          this.state.error = '422'
        } else if (res.status === 409) {
          this.state.error = '409'
        } else if (res.status === 400) {
          this.state.error = '400'
        }
        res.json()
      })
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(console.log);
  };


  render() {
    if (this.state.error === '422') {
      return (
        <div>
          <div className="help-block"><Alert color="danger">Invalid room dimensions. Please fix before
            continuing.</Alert></div>
        </div>
      )
    } else if (this.props.floor === '') {
      return (
        <div className="help-block"><Alert color="success">
          <p>The room has been altered to the following configuration:</p>
          <ul>
            <li>
              Name: {this.props.name}
            </li>
            <li>
              Floor: 0
            </li>
            <li>
              Width: {this.props.width} m
            </li>
            <li>
              Length: {this.props.length} m
            </li>
            <li>
              Height: {this.props.height} m
            </li>
          </ul>
        </Alert>
        </div>
      )
    } else {
      return (
        <div className="help-block"><Alert color="success">
          <p>The room has been altered to the following configuration:</p>
          <ul>
            <li>
              Name: {this.props.name}
            </li>
            <li>
              Floor: {this.props.floor}
            </li>
            <li>
              Width: {this.props.width} m
            </li>
            <li>
              Length: {this.props.length} m
            </li>
            <li>
              Height: {this.props.height} m
            </li>
          </ul>
        </Alert></div>

      )
    }
  }
}

export default US108Put;
