import {
  createEmployeeApi, deleteAllEmployeesApi, getAllEmployeesApi, updateEmployeeSalaryApi,
} from '../../api/EmployeeApi';
import { addEmployeeListSingleEmployee, updateEmployeeList, updateEmployeeListSingleEmployeeSalary } from '../EmployeeListActions';
import {
  apiChainError, apiChainFinishedSuccess, apiChainStarted,
} from '../ApiIndicationActions';
import { clearEmployeeForm } from '../EmployeeFormActions';
import {
  fromApiEmployee, fromApiEmployeeList, fromApiSalary, toApiSalary,
} from '../../static/EmployeeConverter';

const handleEmptyResponse = (response) => {
  if (!response.ok) {
    throw Error();
  }
  return response;
};

const handleJsonResponse = (response) => {
  if (!response.ok) {
    throw Error();
  }
  return response.json();
};

export const refreshAllEmployees = () => (dispatch) => {
  getAllEmployeesApi()
    .then(handleJsonResponse)
    .then((jsonResponse) => {
      dispatch(updateEmployeeList(fromApiEmployeeList(jsonResponse)));
    })
    .catch(() => {});
};

export const createEmployee = (name, salary) => (dispatch) => {
  dispatch(apiChainStarted());
  createEmployeeApi(name, toApiSalary(salary))
    .then(handleJsonResponse)
    .then((jsonResponse) => {
      dispatch(apiChainFinishedSuccess());
      dispatch(clearEmployeeForm());
      dispatch(addEmployeeListSingleEmployee(fromApiEmployee(jsonResponse)));
    })
    .catch(() => dispatch(apiChainError()));
};

export const updateEmployeeSalary = (id, salary) => (dispatch) => {
  dispatch(apiChainStarted());
  updateEmployeeSalaryApi(id, toApiSalary(salary))
    .then(handleJsonResponse)
    .then((jsonResponse) => {
      dispatch(apiChainFinishedSuccess());
      dispatch(
        updateEmployeeListSingleEmployeeSalary(
          jsonResponse.id,
          fromApiSalary(jsonResponse.salary),
        ),
      );
    })
    .catch(() => dispatch(apiChainFinishedSuccess()));
};

export const deleteAllEmployees = () => (dispatch) => {
  dispatch(apiChainStarted());
  deleteAllEmployeesApi()
    .then(handleEmptyResponse)
    .then(() => {
      dispatch(apiChainFinishedSuccess());
      dispatch(refreshAllEmployees());
    })
    .catch(() => dispatch(apiChainError()));
};
