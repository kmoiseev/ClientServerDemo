import {
  API_CHAIN_FINISHED_ERRONEOUSLY,
  API_CHAIN_FINISHED_SUCCESSFULLY,
  API_CHAIN_STARTED,
} from '../actions/types/ApiIndicationActionTypes';

const initialState = {
  inProgress: false,
  successInProgress: false,
  failureInProgress: false,
};

function apiIndicationReducer(state = initialState, action) {
  switch (action.type) {
    case API_CHAIN_STARTED:
      return {
        successInProgress: false,
        inProgress: true,
        failureInProgress: false,
      };
    case API_CHAIN_FINISHED_SUCCESSFULLY:
      return {
        ...state,
        inProgress: false,
        successInProgress: true,
        failureInProgress: false,
      };
    case API_CHAIN_FINISHED_ERRONEOUSLY:
      return {
        ...state,
        inProgress: false,
        successInProgress: false,
        failureInProgress: true,
      };
    default:
      return state;
  }
}

export default apiIndicationReducer;
