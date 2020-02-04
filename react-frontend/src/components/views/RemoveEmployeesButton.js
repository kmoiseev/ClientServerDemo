import React from 'react';
import PropTypes from 'prop-types';
import Button from "@material-ui/core/Button";


const RemoveEmployeesButton = props => (
    <Button
        disabled={props.buttonDisabled}
        onClick={props.handleDeleteAll}
    >
        {props.buttonText}
    </Button>
);

RemoveEmployeesButton.propTypes = {
    buttonDisabled: PropTypes.bool.isRequired,
    buttonText: PropTypes.string.isRequired,
    handleDeleteAll: PropTypes.func.isRequired,
};

export default RemoveEmployeesButton;