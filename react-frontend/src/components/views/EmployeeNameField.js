import React from 'react';
import PropTypes from 'prop-types';
import {TextField} from "@material-ui/core";

const EmployeeNameField = (props) => (
    <TextField
        value={props.value}
        disabled={true}
    />
);

EmployeeNameField.propTypes = {
    value: PropTypes.string.isRequired,
};

export default EmployeeNameField;