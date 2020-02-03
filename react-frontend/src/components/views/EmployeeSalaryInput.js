import React from "react";
import {TextField} from "@material-ui/core";

const EmployeeSalaryInput = (props) => (
    <TextField
        value={toDollars(props.value)}
        onChange={event => props.onChange(event.target.value)}
    />
);

EmployeeSalaryInput.propTypes = {
    value: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
};

export default EmployeeSalaryInput;