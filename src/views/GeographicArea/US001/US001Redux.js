import React from 'react';
import {connect} from 'react-redux';
import {addAreaType} from './Actions001';
import {Button} from "reactstrap";
import MessageU001 from "./MessageU001";

class US001Redux extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      isHidden: true,
      name: ''
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value,
        isHidden: true
      });
    };

  }

  handleSubmit() {
    this.props.onPostType(this.state);
    this.setState({isHidden: false})
  }

  render() {
    const {name} = this.state;
    return (
      <>
        <label> Name:<span>  </span>
          <input value={this.state.name} type="text" name="name" placeholder="Name of the area type"
                 onChange={this.handleInputChange('name')}/>
        </label>
        <p></p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Add area
          type
        </Button>
        {this.state.isHidden === false ?
          <MessageU001 name={this.state.name} addedSensor={this.props.addedSensor}/> : ''}
      </>
    );
  }
}


const mapDispatchToProps = (dispatch) => {
  return {
    onPostType: ({name}) => {
      dispatch(addAreaType({name}))
    }
  }
};
const mapStateToProps = (state) => {
  return {
    loading: state.Reducers001.loading,
    addedSensor: state.Reducers001.addedSensor,
    error: state.Reducers001.error
  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US001Redux);
