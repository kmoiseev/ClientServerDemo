import {EMPLOYEE_LIST_UPDATE_ALL, EMPLOYEE_LIST_UPDATE_SINGLE_SALARY} from "../actions/types/EmployeeListActionTypes";

const initialState = [];

const employeeListReducer = (state = initialState, action) => {
    switch (action.type) {
        case EMPLOYEE_LIST_UPDATE_ALL:
            return action.value;
        case EMPLOYEE_LIST_UPDATE_SINGLE_SALARY:
            return state.map(
                employee =>
                    employee.id === action.value.id ?
                        {...employee, salary: action.value.salary}
                        :
                        employee
            );
        default:
            return state;
    }
};

export default employeeListReducer;