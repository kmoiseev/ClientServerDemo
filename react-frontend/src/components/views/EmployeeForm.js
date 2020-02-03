import React from 'react';
import PropTypes from 'prop-types';
import {Button, Container, TextField} from '@material-ui/core';

const EmployeeForm = (props) => (
    <Container>
        <TextField
            value={props.name}
            onChange={event => props.handleNameChange(event.target.value)}
        />
        <TextField
            value={props.salary}
            onChange={event => props.handleSalaryChange(event.target.value)}
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