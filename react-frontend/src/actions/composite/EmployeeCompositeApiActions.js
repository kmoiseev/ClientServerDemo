import {createEmployeeApi, deleteAllEmployeesApi, getAllEmployeesApi, updateEmployeeSalaryApi} from "../../api/EmployeeApi";
import {updateEmployeeList, updateEmployeeListSingleEmployeeSalary} from "../EmployeeListActions";
import {apiChainFinishedErroneously, apiChainFinishedSuccessfully, apiChainIndicatorOff, startApiChain} from "../ApiIndicationActions";
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

const considerApiSuccess = _ => dispatch => {
    dispatch(apiChainFinishedSuccessfully());
    setTimeout(() => dispatch(apiChainIndicatorOff()), 2000);
};

const considerApiReject = _ => (dispatch) => {
    dispatch(apiChainFinishedErroneously());
    setTimeout(() => dispatch(apiChainIndicatorOff()), 2000);
};

export const createEmployee = (name, salary) => dispatch => {
    dispatch(startApiChain());
    createEmployeeApi(name, toApiSalary(salary))
        .then(handleJsonResponse)
        .then(_ => {
            dispatch(considerApiSuccess());
            dispatch(clearEmployeeForm());
            dispatch(refreshAllEmployees());
        })
        .catch(_ => dispatch(considerApiReject()));
};

export const updateEmployeeSalary = (id, salary) => dispatch => {
    dispatch(startApiChain());
    updateEmployeeSalaryApi(id, toApiSalary(salary))
        .then(handleJsonResponse)
        .then(jsonResponse => {
            dispatch(considerApiSuccess());
            dispatch(
                updateEmployeeListSingleEmployeeSalary(
                    jsonResponse.id,
                    fromApiSalary(jsonResponse.salary)
                )
            );
        })
        .catch(_ => dispatch(considerApiReject()));
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
    dispatch(startApiChain());
    deleteAllEmployeesApi()
        .then(handleEmptyResponse)
        .then(_ => {
            dispatch(considerApiSuccess());
            dispatch(refreshAllEmployees());
        })
        .catch(_ => dispatch(considerApiReject()));
};