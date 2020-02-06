import {EMPLOYEE_LIST_UPDATE_ALL, EMPLOYEE_LIST_UPDATE_SINGLE_SALARY} from "./types/EmployeeListActionTypes";

export const updateEmployeeList = (newList) => ({
    type: EMPLOYEE_LIST_UPDATE_ALL,
    value: newList,
});

export const updateEmployeeListSingleEmployeeSalary = (id, salary) => ({
    type: EMPLOYEE_LIST_UPDATE_SINGLE_SALARY,
    value: {
        id,
        salary,
    }
});