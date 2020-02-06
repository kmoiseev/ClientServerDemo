import {
    API_CHAIN_FINISHED_ERRONEOUSLY,
    API_CHAIN_FINISHED_SUCCESSFULLY, CLEAR_API_CHAIN_INDICATION,
    START_API_CHAIN
} from "../actions/types/ApiIndicationActionTypes";

const initialState = {
    inProgress: false,
    successInProgress: false,
    failureInProgress: false,
};

const apiIndicationReducer = (state = initialState, action) => {
    switch (action.type) {
        case START_API_CHAIN:
            return {
                ...state,
                inProgress: true,
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
        case CLEAR_API_CHAIN_INDICATION:
            return initialState;
        default:
            return state;
    }
};

export default apiIndicationReducer;