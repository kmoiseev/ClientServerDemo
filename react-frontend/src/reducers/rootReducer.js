import { combineReducers } from 'redux'
import employeeFormReducer from "./employeeFormReducer";
import employeeListReducer from "./employeeListReducer";
import validationReducer from "./validationReducer";

const rootReducer = combineReducers({
    employeeForm: employeeFormReducer,
    employeeList: employeeListReducer,
    validation: validationReducer,
});

export default rootReducer;