import { SET_INVALID, SET_VALID } from '../actions/types/ValidationActionTypes';


const initialState = {};

function validationReducer(state = initialState, action) {
  switch (action.type) {
    case SET_INVALID:
      return {
        ...state,
        [action.value]: true,
      };
    case SET_VALID:
      return {
        ...state,
        [action.value]: false,
      };
    default:
      return state;
  }
}

export default validationReducer;
