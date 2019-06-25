import {
  FETCH_ENERGYGRID_INFO_STARTED,
  FETCH_ENERGYGRID_INFO_SUCCESS,
  FETCH_ENERGYGRID_INFO_FAILURE
} from './Actions'

const initialstate = {
  loading: false,
  error: null,
  grid: []
};


export default function Reducers130 (state = initialstate, action) {
  switch (action.type) {
    case FETCH_ENERGYGRID_INFO_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        grid: []
      };
    case FETCH_ENERGYGRID_INFO_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        grid: [action.payload.grid]
      };
    case FETCH_ENERGYGRID_INFO_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        grid: "ERROR: " + action.payload.error,
      };
    default:
      return state;
  }
}
