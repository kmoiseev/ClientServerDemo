import React from "react";
import PropTypes from 'prop-types';
import {CircularProgress} from "@material-ui/core";
import Grid from "@material-ui/core/Grid";

const successPropOrFailure = props => {
    return props.success || props.failure;
};

const ApiIndicator = props => {

    const [needToStop, setNeedToStop] = React.useState(false);
    const [successOrFailure, setSuccessOrFailure] = React.useState(successPropOrFailure(props));
    const [progress, setProgress] = React.useState(0);

    React.useEffect(() => {
        function tick() {
            // reset when reaching 100%
            setProgress(oldProgress => oldProgress >= 100 ? 0 : oldProgress + (needToStop ? 0 : 1));
        }

        const timer = setInterval(tick, 20);
        return () => {
            clearInterval(timer);
        };
    }, []);

    React.useEffect(() => {
        setSuccessOrFailure(prevSuccessOrFailure => {
            const newSuccessOrFailure = successPropOrFailure(props);
            console.log(newSuccessOrFailure);
            if (newSuccessOrFailure !== prevSuccessOrFailure) {
                if (newSuccessOrFailure === true) {
                    setNeedToStop(_ => true);
                } else {
                    setNeedToStop(_ => false);
                }
            }
            return newSuccessOrFailure;
        });
    }, []);

    return (
        <Grid
            container
            direction="row"
            justify="flex-end"
            alignItems="flex-start"
        >
            <Grid item>
                <CircularProgress
                    {...getCircularProgressProps(props)}
                    value={progress}
                    variant="determinate"
                />
            </Grid>
        </Grid>
    );
};

const getCircularProgressProps = (props) => {
    if (props.inProgress) {
        return {
            style: {color: 'blue'},
        };
    } else if (props.success) {
        return {
            style: {color: 'green'},
        };
    } else {
        return {
            style: {color: 'red'},
        };
    }
};

ApiIndicator.propTypes = {
    inProgress: PropTypes.bool.isRequired,
    success: PropTypes.bool.isRequired,
    failure: PropTypes.bool.isRequired,
};

export default ApiIndicator;
