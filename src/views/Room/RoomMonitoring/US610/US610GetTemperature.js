import React, {Component} from 'react';


class US610GetTemperature extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: 0,
    }
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken')
    console.log(this.props);
    fetch("https://smarthome-g2-server.herokuapp.com/rooms/dayMaxTemperature/" + this.props.roomID + "?initialDate=" + this.props.day, {
        headers: {
          'Authorization': token,
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json"
        }
      }
    )
      .then(res => res.json())
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(console.log)
  }


  render() {
    return (
      <div>
        <h5 key={this.state.item}>
          {this.state.item.length !== 0 ? 'There are no readings available for this room' : 'The maximum temperature was' + this.state.item + 'ºC' }
        </h5>
          </div>
    );
  }

}

export default US610GetTemperature;
