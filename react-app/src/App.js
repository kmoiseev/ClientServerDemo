import React from 'react';
import {Container} from "@material-ui/core";
import EmployeeFormContainer from "./components/containers/EmployeeFormContainer";
import EmployeeTableContainer from "./components/containers/EmployeeTableContainer";
import ApiIndicatorContainer from "./components/containers/ApiIndicatorContainer";
import RemoveEmployeesButtonContainer from "./components/containers/RemoveEmployeesButtonContainer";

function App() {
    return (
        <Container>
            <ApiIndicatorContainer />
            <EmployeeFormContainer/>
            <EmployeeTableContainer/>
            <RemoveEmployeesButtonContainer />
        </Container>
    );
}

export default App;
