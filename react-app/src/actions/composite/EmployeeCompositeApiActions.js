import {createEmployeeApi, deleteAllEmployeesApi, getAllEmployeesApi, updateEmployeeSalaryApi} from "../../api/EmployeeApi";
import {updateEmployeeList, updateEmployeeListSingleEmployeeSalary} from "../EmployeeListActions";
import {apiChainError, apiChainFinishedSuccess, apiChainIndicatorOff, apiChainStarted} from "../ApiIndicationActions";
import {clearEmployeeForm} from "../EmployeeFormActions";
import {fromApiEmployeeList, fromApiSalary, toApiSalary} from "../../static/EmployeeConverter";

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

export const createEmployee = (name, salary) => dispatch => {
    dispatch(apiChainStarted());
    createEmployeeApi(name, toApiSalary(salary))
        .then(handleJsonResponse)
        .then(_ => {
            dispatch(apiChainFinishedSuccess());
            dispatch(clearEmployeeForm());
            dispatch(refreshAllEmployees());
        })
        .catch(_ => dispatch(apiChainError()));
};

export const updateEmployeeSalary = (id, salary) => dispatch => {
    dispatch(apiChainStarted());
    updateEmployeeSalaryApi(id, toApiSalary(salary))
        .then(handleJsonResponse)
        .then(jsonResponse => {
            dispatch(apiChainFinishedSuccess());
            dispatch(
                updateEmployeeListSingleEmployeeSalary(
                    jsonResponse.id,
                    fromApiSalary(jsonResponse.salary)
                )
            );
        })
        .catch(_ => dispatch(apiChainFinishedSuccess()));
};

export const refreshAllEmployees = () => (dispatch) => {
    getAllEmployeesApi()
        .then(handleJsonResponse)
        .then(jsonResponse => {
            dispatch(updateEmployeeList(fromApiEmployeeList(jsonResponse)));
        })
        .catch(_ => {});
};

export const deleteAllEmployees = () => (dispatch) => {
    dispatch(apiChainStarted());
    deleteAllEmployeesApi()
        .then(handleEmptyResponse)
        .then(_ => {
            dispatch(apiChainFinishedSuccess());
            dispatch(refreshAllEmployees());
        })
        .catch(_ => dispatch(apiChainError()));
};