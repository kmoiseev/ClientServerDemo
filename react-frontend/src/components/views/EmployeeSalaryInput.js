import React from "react";
import PropTypes from 'prop-types';
import {TextField} from "@material-ui/core";

const validateInput = (input) => {
    if (!input) {
        return "Cannot be empty";
    }
    if (!/^\d+\.?\d{0,2}$/.test(input)) {
        return "Must be a positive number with up to two decimal places"
    }
};

const proxyChange = (input, props) => {
    if (validateInput(input)) {
        props.onInvalid();
    } else {
        props.onValid();
    }
    props.onChange(input);
};

const EmployeeSalaryInput = (props) => (
    <TextField
        label={props.label}
        value={props.value}
        onChange={event => proxyChange(event.target.value, props)}
        error={props.isInvalid}
        helperText={props.isInvalid && validateInput(props.value)}
    />
);

EmployeeSalaryInput.propTypes = {
    label: PropTypes.string,
    value: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    onValid: PropTypes.func.isRequired,
    onInvalid: PropTypes.func.isRequired,
    isInvalid: PropTypes.bool.isRequired,
};

export default EmployeeSalaryInput;