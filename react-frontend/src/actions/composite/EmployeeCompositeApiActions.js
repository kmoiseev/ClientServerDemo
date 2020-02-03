import {
    createEmployeeApi,
    deleteAllEmployeesApi,
    getAllEmployeesApi,
    updateEmployeeSalaryApi
} from "../../api/EmployeeApi";
import {updateEmployeeList, updateEmployeeListSingleEmployeeSalary} from "../EmployeeListActions";
import {
    apiChainFinishedErroneously,
    apiChainFinishedSuccessfully,
    apiChainIndicatorOff,
    startApiChain
} from "../ApiIndicationActions";
import {clearEmployeeForm} from "../EmployeeFormActions";

const handleJsonResponse = (response) => {
    if (!response.ok) {
        throw Error();
    }
    return response.json();
};

const considerApiSuccess = _ => dispatch => {
    dispatch(apiChainFinishedSuccessfully());
    setTimeout(() => dispatch(apiChainIndicatorOff()), 2000);
};

const considerApiReject = () => (dispatch) => {
    dispatch(apiChainFinishedErroneously());
    setTimeout(() => dispatch(apiChainIndicatorOff()), 2000);
};

export const createEmployee = (name, salary) => (dispatch) => {
    dispatch(startApiChain());
    createEmployeeApi(name, salary)
        .then(handleJsonResponse)
        .then(_ => {
            dispatch(considerApiSuccess());
            dispatch(clearEmployeeForm());
            dispatch(refreshAllEmployees());
        })
        .catch(_ => dispatch(considerApiReject()));
};

export const updateEmployeeSalary = (id, salary) => (dispatch) => {
    dispatch(startApiChain());
    updateEmployeeSalaryApi(id, salary)
        .then(handleJsonResponse)
        .then(jsonResponse => {
            dispatch(considerApiSuccess());
            dispatch(updateEmployeeListSingleEmployeeSalary(jsonResponse.id, jsonResponse.salary));
        })
        .catch(_ => dispatch(considerApiReject()));
};

export const refreshAllEmployees = () => (dispatch) => {
    dispatch(startApiChain());
    getAllEmployeesApi()
        .then(handleJsonResponse)
        .then(jsonResponse => {
            dispatch(considerApiSuccess());
            dispatch(updateEmployeeList(jsonResponse));
        })
        .catch(_ => dispatch(considerApiReject()));
};

export const deleteAllEmployees = () => (dispatch) => {
    dispatch(startApiChain());
    deleteAllEmployeesApi()
        .then(handleJsonResponse)
        .then(_ => {
            dispatch(considerApiSuccess());
            dispatch(refreshAllEmployees());
        })
        .catch(_ => dispatch(considerApiReject()));
};