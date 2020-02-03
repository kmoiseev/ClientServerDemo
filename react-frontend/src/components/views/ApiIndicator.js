import React from "react";
import PropTypes from 'prop-types';
import {CircularProgress, Container} from "@material-ui/core";
import {green, red} from "@material-ui/core/colors";

const ApiIndicator = props => (
    <Container>
        {props.visible && render(props)}
    </Container>
);

const render = (props) => {
    if (props.inProgress) {
        return renderInProgress();
    } else if (props.success) {
        return renderSuccess();
    } else if (props.failure) {
        return renderFailure();
    }
};

const renderInProgress = _ => (
    <CircularProgress />
);

const renderSuccess = _ => (
    <CircularProgress variant="static" value={100} style={{color: 'green'}} />
);

const renderFailure = _ => (
    <CircularProgress variant="static" value={100} style={{color: 'red'}} />
);

ApiIndicator.propTypes = {
    visible: PropTypes.bool.isRequired,
    inProgress: PropTypes.bool.isRequired,
    success: PropTypes.bool.isRequired,
    failure: PropTypes.bool.isRequired,
};

export default ApiIndicator;
