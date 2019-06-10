import React, {Component} from 'react';
import TableHeader from "./TableHeader";
import {Button} from "reactstrap";
import {deleteRoomFromGrid} from "../US149/Actions";
import {connect} from 'react-redux';
import removeFromGrid from "./gridRedux/removeFromGrid"


class TableBody extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      sensors: false,
      gridID: ''
    }
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    fetch('https://localhost:8443/gridSettings/grids/' + this.props.gridID, {
      headers: {
        'Authorization': token,
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json"
      }
    })
      .then(res => res.json())
      .then((json) => {
        this.setState({
          sensors: true,
          item: json,
        })
      })
      .catch(console.log)
  }

  render() {
    const headers = {
      name: "Rooms",
      floor: "Floor",
      height: "Height (m)",
      length: "Length (m)",
      width: "Width (m)",
      remove: "Remove from grid",
      edit: "Edit Room"
    };
    var {id, item} = this.state;
    return (
      <>
        <TableHeader headers={headers}/>
        {item.map(item => (
          <tr key={item.name}>
            <td> {item.name}</td>
            <td>{item.floor} </td>
            <td> {item.height}</td>
            <td> {item.length} </td>
            <td> {item.width} </td>
            <td>
              <removeFromGrid gridID={this.props.gridID} roomID={item.name}/>
            </td>
            <td> {item.edit} </td>
          </tr>
        ))}
      </>
    );
  }

}


export default TableBody;
