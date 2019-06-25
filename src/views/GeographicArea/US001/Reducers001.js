import {
  POST_AREATYPE_STARTED,
  POST_AREATYPE_SUCCESS,
  POST_AREATYPE_FAILURE,

} from './Actions001'

const initialstate = {
  loading: false,
  error: null,
  addedSensor: []
};

export default function Reducers001(state = initialstate, action) {
  switch (action.type) {
    case POST_AREATYPE_STARTED:
      return {
        ...state,
        loading: true,
        error: null,
        addedSensor: []
      };
    case POST_AREATYPE_SUCCESS:
      return {
        ...state,
        loading: false,
        error: null,
        addedSensor: [action.payload.addedArea]
      };
    case POST_AREATYPE_FAILURE:
      return {
        ...state,
        loading: false,
        error: action.payload.error,
        addedSensor: "ERROR: " + action.payload.error
      };

    default:
      return state;
  }
}
