import React from 'react';
import './App.css';
import {Container} from "@material-ui/core";
import EmployeeFormContainer from "./components/containers/EmployeeFormContainer";
import EmployeeTableContainer from "./components/containers/EmployeeTableContainer";

function App() {
    return (
        <Container>
            <EmployeeFormContainer/>
            <EmployeeTableContainer/>
        </Container>
    );
}

export default App;
