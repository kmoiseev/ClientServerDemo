import {
    createEmployeeApi,
    deleteAllEmployeesApi,
    getAllEmployeesApi,
    updateEmployeeSalaryApi
} from "../../api/EmployeeApi";
import {updateEmployeeList} from "../EmployeeListActions";

export const createEmployee = (name, salary) => (dispatch) => {
    dispatch(createEmployeeApi(name, salary)).then(
        () => getAllEmployees()
    );
};

export const updateEmployeeSalary = (id, salary) => (dispatch) => {
    dispatch(updateEmployeeSalaryApi(id, salary)).then(
        () => getAllEmployees()
    );
};

export const getAllEmployees = () => (dispatch) => {
  dispatch(getAllEmployeesApi()).then(
      response => updateEmployeeList(response)
  );
};

export const deleteAllEmployees = () => (dispatch) => {
    dispatch(deleteAllEmployeesApi()).then(
        () => getAllEmployees()
    );
};