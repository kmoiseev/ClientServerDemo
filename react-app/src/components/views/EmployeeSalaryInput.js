import React from "react";
import PropTypes from 'prop-types';
import {TextField} from "@material-ui/core";

const regExpPositiveNumber = /^[0-9]*\.?[0-9]*$/;
const regExpPositiveNumberWithTwoDecimals = /^\d+\.?\d{0,2}$/;
const maximumNumber = 9999999999999.99;

const validateInput = (input) => {
    if (!input) {
        return "Cannot be empty";
    }
    if (!regExpPositiveNumber.test(input)) {
        return "Must be positive number";
    }
    if (!regExpPositiveNumberWithTwoDecimals.test(input)) {
        return "Up to two decimal places allowed"
    }
    if (Number(input) > maximumNumber) {
        return "Cannot exceed " + maximumNumber;
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
        {...props}
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