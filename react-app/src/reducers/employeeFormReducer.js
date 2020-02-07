import { EMPLOYEE_FORM_CLEAR, EMPLOYEE_FORM_UPDATE_NAME, EMPLOYEE_FORM_UPDATE_SALARY } from '../actions/types/EmployeeFormActionTypes';

const initialSate = {
  name: '',
  salary: '',
};

function employeeFormReducer(state = initialSate, action) {
  switch (action.type) {
    case EMPLOYEE_FORM_UPDATE_NAME:
      return {
        ...state,
        name: action.value,
      };
    case EMPLOYEE_FORM_UPDATE_SALARY:
      return {
        ...state,
        salary: action.value,
      };
    case EMPLOYEE_FORM_CLEAR:
      return initialSate;
    default:
      return state;
  }
}

export default employeeFormReducer;
