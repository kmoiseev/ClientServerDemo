import React from 'react';
import PropTypes from 'prop-types';
import { CircularProgress, Fade } from '@material-ui/core';
import Grid from '@material-ui/core/Grid';

const getCircularProgressStyle = (props) => {
  let color = 'blue';
  if (props.success) {
    color = 'green';
  } else if (props.failure) {
    color = 'red';
  }
  return {
    color,
  };
};

function ApiIndicator(props) {
  const { inProgress, success, failure } = props;

  const [fade, setFade] = React.useState(false);
  const [progress, setProgress] = React.useState(0);
  const [progressChangingAfterMax, setProgressChangingAfterMax] = React.useState(false);

  React.useEffect(() => {
    const tick = (_) => {
      setProgress((oldProgress) => {
        if (progressChangingAfterMax) {
          return oldProgress >= 100 && progressChangingAfterMax ? 0 : oldProgress + 2;
        }
        return oldProgress < 100 ? oldProgress + 2 : oldProgress;
      });
    };

    const timer = setInterval(tick, 20);
    return () => clearInterval(timer);
  });

  React.useEffect(() => {
    if (inProgress) {
      setFade(true);
      setProgress(0);
      setProgressChangingAfterMax(true);
    }
    if (success || failure) {
      setProgressChangingAfterMax(false);

      const timer = setTimeout((_) => setFade(false), 1000);
      return () => clearTimeout(timer);
    }

    return undefined;
  }, [inProgress, success, failure]);

  return (
        <Grid
            container
            direction="row"
            justify="flex-end"
            alignItems="flex-start"
        >
            <Fade in={fade} timeout={250}>
                <Grid item>
                    <CircularProgress
                        style={getCircularProgressStyle(props)}
                        value={progress}
                        variant="determinate"
                    />
                </Grid>
            </Fade>
        </Grid>
  );
}

ApiIndicator.propTypes = {
  inProgress: PropTypes.bool.isRequired,
  success: PropTypes.bool.isRequired,
  failure: PropTypes.bool.isRequired,
};

export default ApiIndicator;
