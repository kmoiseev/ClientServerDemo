import {createEmployeeApi, deleteAllEmployeesApi, getAllEmployeesApi, updateEmployeeSalaryApi} from "../../api/EmployeeApi";
import {updateEmployeeList, updateEmployeeListSingleEmployeeSalary} from "../EmployeeListActions";
import {apiChainFinishedErroneously, apiChainFinishedSuccessfully, startApiChain} from "../ApiIndicationActions";
import {clearEmployeeForm} from "../EmployeeFormActions";

const handleJsonResponse = (response) => {
    if (!response.ok) {
        throw Error();
    }
    return response.json();
};

const handleApiReject = () => (dispatch) => {
    dispatch(apiChainFinishedErroneously())
};

export const createEmployee = (name, salary) => (dispatch) => {
    dispatch(startApiChain());
    createEmployeeApi(name, salary)
        .then(handleJsonResponse)
        .then(_ => {
            dispatch(clearEmployeeForm());
            dispatch(refreshAllEmployees());
        })
        .catch(_ => dispatch(handleApiReject()));
};

export const updateEmployeeSalary = (id, salary) => (dispatch) => {
    dispatch(startApiChain());
    updateEmployeeSalaryApi(id, salary)
        .then(handleJsonResponse)
        .then(json => dispatch(updateEmployeeListSingleEmployeeSalary(json.id, json.salary)))
        .catch(_ => dispatch(handleApiReject()));
};

export const refreshAllEmployees = () => (dispatch) => {
    getAllEmployeesApi()
        .then(handleJsonResponse)
        .then(jsonResponse => dispatch(updateEmployeeList(jsonResponse)))
        .catch(_ => dispatch(handleApiReject()));
};

export const deleteAllEmployees = () => (dispatch) => {
    deleteAllEmployeesApi()
        .then(handleJsonResponse)
        .then(_ => dispatch(refreshAllEmployees()))
        .catch(_ => dispatch(handleApiReject()));
};