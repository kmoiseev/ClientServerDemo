import React from 'react';
import './App.css';
import {CircularProgress, Container} from "@material-ui/core";
import EmployeeFormContainer from "./components/containers/EmployeeFormContainer";
import EmployeeTableContainer from "./components/containers/EmployeeTableContainer";
import ApiIndicatorContainer from "./components/containers/ApiIndicatorContainer";

function App() {
    return (
        <Container>
            <ApiIndicatorContainer />
            <EmployeeFormContainer/>
            <EmployeeTableContainer/>
        </Container>
    );
}

export default App;
