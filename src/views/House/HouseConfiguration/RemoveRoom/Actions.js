import axios from 'axios';

export const REMOVE_ROOM_STARTED = 'REMOVE_ROOM_STARTED';
export const REMOVE_ROOM_SUCCESS = 'REMOVE_ROOM_SUCCESS';
export const REMOVE_ROOM_FAILURE = 'REMOVE_ROOM_FAILURE';


export function removeRoom ({link}){
  const token = localStorage.getItem('loginToken');
  console.log(link)
  return dispatch => {
    dispatch(fetchRoomLink(link));
    const data = {link};
    axios
      .put(link, data,
        {
          headers: {
            'Authorization': token,
            "Access-Control-Allow-Credentials": true,
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json"
          }
        })
      .then(res => {
        dispatch(fetchRoomRemoveSuccess(res.data));
      })
      .catch(err => {
        dispatch(fetchRoomRemoveFailure(err.message));
      });
  };
};


export function fetchRoomLink(link) {
  return {
    type: REMOVE_ROOM_STARTED,
    payload: {
      link: link,
    }
  }
}

export function fetchRoomRemoveSuccess(data) {
  return {
    type: REMOVE_ROOM_SUCCESS,
    payload: {
      message: data,
    }
  }
}

export function fetchRoomRemoveFailure(message) {
  return {
    type: REMOVE_ROOM_FAILURE,
    payload: {
      error: message
    }
  }
}
