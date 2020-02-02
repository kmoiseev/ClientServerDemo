import {EMPLOYEE_LIST_UPDATE_ALL} from "../actions/types/EmployeeListActionTypes";

const initialState = [];

const employeeListReducer = (state = initialState, action) => {
    switch (action.type) {
        case EMPLOYEE_LIST_UPDATE_ALL:
            return action.value;
        default:
            return state;
    }
};

export default employeeListReducer;