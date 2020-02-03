import React from 'react';
import PropTypes from 'prop-types';
import {TextField} from "@material-ui/core";

const validateInput = (input) => {
    if (!input) {
        return "Cannot be empty";
    }
};

const EmployeeNameInput = (props) => (
    <TextField
        value={props.value}
        onChange={event => props.onChange(event.target.value)}
        error={props.showValidationError && validateInput(Number(props.value))}
        helperText={props.showValidationError && validateInput(Number(props.value))}
    />
);

EmployeeNameInput.propTypes = {
    value: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    showValidationError: PropTypes.bool.isRequired,
};

export default EmployeeNameInput;