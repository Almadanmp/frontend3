import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {removeRoom} from "./Actions";
import {connect} from 'react-redux';
import {confirmAlert} from 'react-confirm-alert'; // Import
import 'react-confirm-alert/src/react-confirm-alert.css'; // Import css


class RemoveRoom extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);


  }

  submit = () => {
    confirmAlert({
      title: 'Confirm to remove',
      message: 'Are you sure to remove ' + this.props.name + '?',
      buttons: [
        {
          label: 'Yes',
          onClick: () => this.handleSubmit()
        },
        {
          label: 'No',
          onClick: () => {
          }
        }
      ]
    });
  };

  handleSubmit() {
    this.props.onRemoveRoom(this.props.link.href);
  }

  render() {
    return (
      <>
        <Button style={{backgroundColor: '#ffffff', marginBottom: '1rem'}} onClick={this.submit}>
          <i className="fa fa-minus-square-o fa-lg"></i>
        </Button>
      </>
    )
  }
}

const mapDispatchToProps = (dispatch) => {
  return {
    onRemoveRoom: (link) => {
      dispatch(removeRoom({link}))
    }
  }
};

export default connect(null, mapDispatchToProps)(RemoveRoom);
