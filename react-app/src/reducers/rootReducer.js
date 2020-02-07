import { combineReducers } from 'redux';
import employeeFormReducer from './employeeFormReducer';
import employeeListReducer from './employeeListReducer';
import validationReducer from './validationReducer';
import apiIndicationReducer from './apiIndicationReducer';

const rootReducer = combineReducers({
  employeeForm: employeeFormReducer,
  employeeList: employeeListReducer,
  validation: validationReducer,
  apiIndication: apiIndicationReducer,
});

export default rootReducer;
