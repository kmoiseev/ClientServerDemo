import {SECURITY_PWD, SECURITY_UNAME, SERVER_PORT} from "./ApiEnvironment";

const urlPrefix = `http://localhost:${SERVER_PORT}/employee/`;

function fetchWithDefaults(url, method, body) {
    const authString = 'Basic ' + btoa(`${SECURITY_UNAME}:${SECURITY_PWD}`);
    return fetch(url, {
        method,
        headers: {
            'Authorization': authString,
            'Accept': '*/*',
            'Content-Type': 'application/json',
        },
        body: body,
        cache: 'no-store'
    });
}

export function createEmployeeApi(name, salary) {
    return fetchWithDefaults(
        urlPrefix + "create",
        'POST',
        JSON.stringify({
            name,
            salary
        }),
    );
}

export function updateEmployeeSalaryApi(employeeId, salary) {
    return fetchWithDefaults(
        `${urlPrefix}update/${employeeId}/salary/${salary}`,
        'PATCH'
    );
}

export function getAllEmployeesApi() {
    return fetchWithDefaults(
        urlPrefix + "get/all/sort/id/asc",
        'GET'
    );
}

export function deleteAllEmployeesApi() {
    return fetchWithDefaults(
        urlPrefix + "delete/all",
        'DELETE'
    );
}