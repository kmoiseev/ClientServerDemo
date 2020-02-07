import {
  EMPLOYEE_FORM_CLEAR,
  EMPLOYEE_FORM_UPDATE_NAME,
  EMPLOYEE_FORM_UPDATE_SALARY,
} from './types/EmployeeFormActionTypes';

export const updateEmployeeFormName = (name) => ({
  type: EMPLOYEE_FORM_UPDATE_NAME,
  value: name,
});

export const updateEmployeeFormSalary = (salary) => ({
  type: EMPLOYEE_FORM_UPDATE_SALARY,
  value: salary,
});

export const clearEmployeeForm = () => ({
  type: EMPLOYEE_FORM_CLEAR,
});
