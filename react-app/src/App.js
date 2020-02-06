import React from 'react';
import {createMuiTheme} from "@material-ui/core";
import EmployeeFormContainer from "./components/containers/EmployeeFormContainer";
import EmployeeTableContainer from "./components/containers/EmployeeTableContainer";
import ApiIndicatorContainer from "./components/containers/ApiIndicatorContainer";
import RemoveEmployeesButtonContainer from "./components/containers/RemoveEmployeesButtonContainer";
import Grid from "@material-ui/core/Grid";
import {ThemeProvider} from "@material-ui/styles";

function App() {
    const theme = createMuiTheme({
        spacing: 8
    });
    return (
        <ThemeProvider theme={theme}>
            <Grid
                container
                direction="column"
                justify="center"
                alignItems="center"
                spacing={2}
            >
                <ApiIndicatorContainer/>
                <EmployeeFormContainer/>
                <EmployeeTableContainer/>
                <RemoveEmployeesButtonContainer/>
            </Grid>
        </ThemeProvider>
    );
}

export default App;
