import React from 'react';
import PropTypes from 'prop-types';
import {Button, Table, TableCell, TableContainer, TableHead, TableRow, TextField, TableBody} from "@material-ui/core";

const EmployeeTable = (props) => (
    <TableContainer>
        <Table>
            <TableHead>
                <TableRow>
                    <TableCell>Name</TableCell>
                    <TableCell>Salary</TableCell>
                    <TableCell/>
                </TableRow>
            </TableHead>
            <TableBody>
                {props.employees.map(employee => renderEmployee(props, employee))}
            </TableBody>
        </Table>
    </TableContainer>
);

const renderEmployee = (props, employee) => (
    <TableRow>
        <TableCell>{employee.name}</TableCell>
        <TableCell>
            <TextField
                value={employee.salary}
                onChange={event => props.handleSalaryChangeForEmployee(employee.id, event.target.value)}
            />
        </TableCell>
        <TableCell>
            <Button
                onClick={() => props.handleButtonClickForEmployee(employee.id, employee.salary)}
            >
                {props.buttonText}
            </Button>
        </TableCell>
    </TableRow>
);

EmployeeTable.propTypes = {
    employees: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.number.isRequired,
            name: PropTypes.string.isRequired,
            salary: PropTypes.number.isRequired,
        })
    ),
    handleButtonClickForEmployee: PropTypes.func.isRequired,
    handleSalaryChangeForEmployee: PropTypes.func.isRequired,
};

export default EmployeeTable;