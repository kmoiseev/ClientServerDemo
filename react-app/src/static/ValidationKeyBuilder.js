const EMPLOYEE_FORM_NAME_KEY = "employeeFormName";
const EMPLOYEE_FORM_SALARY_KEY = "employeeFormSalary";
const EMPLOYEE_LIST_SALARY_KEY_PREFIX = "employeeList-";

export const getKeyForEmployeeFormName = () => EMPLOYEE_FORM_NAME_KEY;

export const getKeyForEmployeeFormSalary = () => EMPLOYEE_FORM_SALARY_KEY;

export const getKeyForEmployeeList = (employeeId) => EMPLOYEE_LIST_SALARY_KEY_PREFIX + employeeId;