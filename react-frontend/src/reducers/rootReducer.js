import { combineReducers } from 'redux'
import employeeFormReducer from "./employeeFormReducer";
import employeeListReducer from "./employeeListReducer";

const rootReducer = combineReducers({
    employeeForm: employeeFormReducer,
    employeeList: employeeListReducer,
});

export default rootReducer;