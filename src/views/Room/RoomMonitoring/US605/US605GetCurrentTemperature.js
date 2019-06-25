import React, {Component} from 'react';


class US605GetCurrentTemperature extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
    }
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken')
    fetch('https://smarthome-g2-server.herokuapp.com/rooms/currentRoomTemperature/'+this.props.roomID, {
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
    var {item} = this.state;
    console.log(item)
    return (
      <div>
        <p></p>
        {item.length == 0 ? 'No data available' : item.toString() + 'Cº'}
      </div>
    );
  }
}

export default US605GetCurrentTemperature;
