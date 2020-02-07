import React from 'react';
import PropTypes from 'prop-types';
import Grid from '@material-ui/core/Grid';

const AppHeader = (props) => (
    <Grid container direction="row" justify="center">
      <Grid item>
        <h1>{props.headerText}</h1>
      </Grid>
    </Grid>
);

AppHeader.propTypes = {
  headerText: PropTypes.string.isRequired,
};

export default AppHeader;
