import React from 'react';
import { createMuiTheme } from '@material-ui/core';
import Grid from '@material-ui/core/Grid';
import { ThemeProvider } from '@material-ui/styles';
import Container from '@material-ui/core/Container';
import EmployeeFormContainer from './components/containers/EmployeeFormContainer';
import EmployeeTableContainer from './components/containers/EmployeeTableContainer';
import ApiIndicatorContainer from './components/containers/ApiIndicatorContainer';
import RemoveEmployeesButtonContainer from './components/containers/RemoveEmployeesButtonContainer';
import AppHeader from './components/views/AppHeader';

function App() {
  const theme = createMuiTheme({
    spacing: 8,
  });
  return (
        <ThemeProvider theme={theme}>
            <Container maxWidth="xl">
                <Grid
                    container
                    direction="column"
                    justify="flex-start"
                    alignItems="stretch"
                    spacing={5}
                >
                    <Grid item>
                        <ApiIndicatorContainer/>
                    </Grid>
                    <Grid item>
                        <AppHeader headerText="Employees Application Demo"/>
                    </Grid>
                    <Grid item>
                        <EmployeeFormContainer/>
                    </Grid>
                    <Grid item>
                        <EmployeeTableContainer/>
                    </Grid>
                    <Grid item>
                        <RemoveEmployeesButtonContainer/>
                    </Grid>
                </Grid>
            </Container>
        </ThemeProvider>
  );
}

export default App;
