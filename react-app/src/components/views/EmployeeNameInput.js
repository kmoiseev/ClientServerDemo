import React from 'react';
import PropTypes from 'prop-types';
import { TextField } from '@material-ui/core';

function validateInput(input) {
  if (!input) {
    return 'Cannot be empty';
  }
  return undefined;
}

function proxyChange(input, props) {
  if (validateInput(input)) {
    props.onInvalid();
  } else {
    props.onValid();
  }
  props.onChange(input);
}

const EmployeeNameInput = (props) => (
    <TextField
        {...props}
        label={props.label}
        value={props.value}
        onChange={(event) => proxyChange(event.target.value, props)}
        error={props.isInvalid}
        helperText={props.isInvalid && validateInput(props.value)}
    />
);

EmployeeNameInput.propTypes = {
  label: PropTypes.string,
  value: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
  onValid: PropTypes.func.isRequired,
  onInvalid: PropTypes.func.isRequired,
  isInvalid: PropTypes.bool.isRequired,
};

export default EmployeeNameInput;
