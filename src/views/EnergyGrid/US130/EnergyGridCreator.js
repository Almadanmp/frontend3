import React from 'react';
import 'react-day-picker/lib/style.css';
import {Button} from "reactstrap";
import {fetchEnergyGrid} from "./Actions";
import {connect} from 'react-redux';
import CreateGrid from "./CreateGrid";

class EnergyGridCreator extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      isHidden: true,
      name: '',
      maxContractedPower: ''
    };

    this.handleInputChange = attribute => event => {
      this.setState({
        [attribute]: event.target.value,
        isHidden: true
      });
    };
  }

  toggleHidden = ()=>this.setState((prevState)=>({isHidden: !prevState.isHidden}))


  handleSubmit(){
    this.props.onFetchEnergyGrid(this.state);
    this.toggleHidden();
  }

  render() {
    const {name, maxContractedPower} = this.state;
    return (
      <>
        <label> Name: <input placeholder={"Name"} value={this.state.name} type="text" name="name" onChange={this.handleInputChange('name')}/>
        </label>
        <p></p>
        <label> Maximum Power: <input placeholder={"0"} value={this.state.maxContractedPower} type="number" min="0" name="maxContractedPower" onChange={this.handleInputChange('maxContractedPower')}/>
        </label>
        <p></p>
        <Button style={{backgroundColor: '#e4e5e6', marginBottom: '1rem'}} onClick={this.handleSubmit}>Save new energy grid</Button>
        {!this.state.isHidden && <CreateGrid grid={this.props.grid} error={this.props.error}/>}

      </>
    )
  }
}

const mapStateToProps = (state) => {
  return {
    loading: state.Reducers130.loading,
    grid: state.Reducers130.grid,
    error: state.Reducers130.error
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchEnergyGrid: ({name, maxContractedPower}) => {
      dispatch(fetchEnergyGrid({name, maxContractedPower}))
    }
  }
};

export default connect(mapStateToProps,mapDispatchToProps)(EnergyGridCreator);
