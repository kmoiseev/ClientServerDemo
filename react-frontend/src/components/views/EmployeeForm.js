import React from 'react';
import PropTypes from 'prop-types';
import {Button, Container} from '@material-ui/core';
import EmployeeNameInput from "./EmployeeNameInput";
import EmployeeSalaryInput from "./EmployeeSalaryInput";

const EmployeeForm = (props) => (
    <Container>
        <EmployeeNameInput
            label={props.nameInputLabel}
            value={props.name}
            onChange={value => props.handleNameChange(value)}
            onInvalid={props.markNameInvalid}
            onValid={props.markNameValid}
            isInvalid={props.isNameInvalid}
        />
        <EmployeeSalaryInput
            label={props.salaryInputLabel}
            value={props.salary}
            onChange={value => props.handleSalaryChange(value)}
            onInvalid={props.markSalaryInvalid}
            onValid={props.markSalaryValid}
            isInvalid={props.isSalaryInvalid}
        />
        <Button
            onClick={() => props.handleButtonClick(props.name, props.salary)}
            disabled={props.isButtonDisabled}
        >
            {props.buttonText}
        </Button>
    </Container>
);

EmployeeForm.propTypes = {
    name: PropTypes.string.isRequired,
    salary: PropTypes.string.isRequired,
    buttonText: PropTypes.string.isRequired,
    nameInputLabel: PropTypes.string.isRequired,
    salaryInputLabel: PropTypes.string.isRequired,
    isButtonDisabled: PropTypes.bool.isRequired,

    handleNameChange: PropTypes.func.isRequired,
    handleSalaryChange: PropTypes.func.isRequired,
    handleButtonClick: PropTypes.func.isRequired,

    markNameValid: PropTypes.func.isRequired,
    markNameInvalid: PropTypes.func.isRequired,
    isNameInvalid: PropTypes.bool.isRequired,

    markSalaryValid: PropTypes.func.isRequired,
    markSalaryInvalid: PropTypes.func.isRequired,
    isSalaryInvalid: PropTypes.bool.isRequired,
};

export default EmployeeForm;