import React from 'react';
import PropTypes from 'prop-types';
import {
  Button, Table, TableBody, TableCell, TableContainer, TableHead, TableRow,
} from '@material-ui/core';
import EmployeeNameField from './EmployeeNameField';
import EmployeeSalaryInput from './EmployeeSalaryInput';

const renderEmployee = (props, employee) => (
    <TableRow key={employee.id}>
        <TableCell>
            <EmployeeNameField
                value={employee.name}
            />
        </TableCell>
        <TableCell>
            <EmployeeSalaryInput
                value={employee.salary}
                onChange={(value) => props.handleSalaryChangeForEmployee(employee.id, value)}
                onInvalid={() => props.markSalaryInvalid(employee.id)}
                onValid={() => props.markSalaryValid(employee.id)}
                isInvalid={props.isSalaryInvalid(employee.id)}
            />
        </TableCell>
        <TableCell>
            <Button
                onClick={() => props.handleButtonClickForEmployee(employee.id, employee.salary)}
                disabled={props.isUpdateButtonDisabled(employee.id)}
                variant="contained"
            >
                {props.buttonText}
            </Button>
        </TableCell>
    </TableRow>
);

const EmployeeTable = (props) => (
    <TableContainer>
        <Table>
            <TableHead>
                <TableRow>
                    <TableCell><h2>Name</h2></TableCell>
                    <TableCell><h2>Salary</h2></TableCell>
                    <TableCell/>
                </TableRow>
            </TableHead>
            <TableBody>
                {props.employees.map((employee) => renderEmployee(props, employee))}
            </TableBody>
        </Table>
    </TableContainer>
);

EmployeeTable.propTypes = {
  employees: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number.isRequired,
      name: PropTypes.string.isRequired,
      salary: PropTypes.string.isRequired,
    }),
  ),
  isUpdateButtonDisabled: PropTypes.func.isRequired,

  handleButtonClickForEmployee: PropTypes.func.isRequired,
  handleSalaryChangeForEmployee: PropTypes.func.isRequired,

  markSalaryInvalid: PropTypes.func.isRequired,
  markSalaryValid: PropTypes.func.isRequired,
  isSalaryInvalid: PropTypes.func.isRequired,
};

export default EmployeeTable;
