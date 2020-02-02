import {EMPLOYEE_LIST_UPDATE_ALL} from "./types/EmployeeListActionTypes";

export const updateEmployeeList = (newList) => ({
    type: EMPLOYEE_LIST_UPDATE_ALL,
    value: newList,
});