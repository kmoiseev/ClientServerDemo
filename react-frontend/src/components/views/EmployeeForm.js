import React from 'react';
import PropTypes from 'prop-types';
import {Button, Container} from '@material-ui/core';
import EmployeeNameInput from "./EmployeeNameInput";
import EmployeeSalaryInput from "./EmployeeSalaryInput";

const EmployeeForm = (props) => (
    <Container>
        <EmployeeNameInput
            value={props.name}
            onChange={value => props.handleNameChange(value)}
            showValidationError
        />
        <EmployeeSalaryInput
            value={props.salary}
            onChange={value => props.handleSalaryChange(value)}
            showValidationError
        />
        <Button
            onClick={() => props.handleButtonClick(props.name, props.salary)}
        >
            {props.buttonText}
        </Button>
    </Container>
);

EmployeeForm.propTypes = {
    name: PropTypes.string.isRequired,
    salary: PropTypes.number.isRequired,
    buttonText: PropTypes.string.isRequired,
    handleNameChange: PropTypes.func.isRequired,
    handleSalaryChange: PropTypes.func.isRequired,
    handleButtonClick: PropTypes.func.isRequired,
};

export default EmployeeForm;