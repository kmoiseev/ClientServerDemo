import React from "react";
import {TextField} from "@material-ui/core";

const EmployeeSalaryButton = (props) => (
    <TextField
        type='number'
        value={props.value}
        onChange={props.onChange}
    />
);

EmployeeSalaryButton.propTypes = {
    value: PropTypes.number.isRequired,
    onChange: PropTypes.func.isRequired,
};