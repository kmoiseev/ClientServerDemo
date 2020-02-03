import {connect} from 'react-redux'
import {getEmployeeFormName, getEmployeeFormSalary} from "../../selectors/EmployeeFormSelector";
import EmployeeForm from "../views/EmployeeForm"
import {updateEmployeeFormName, updateEmployeeFormSalary} from "../../actions/EmployeeFormActions";
import {createEmployee} from "../../actions/composite/EmployeeCompositeActions";

const mapStateToProps = (state) => ({
    name: getEmployeeFormName(state),
    salary: getEmployeeFormSalary(state),
    buttonText: "Create",
});

const mapDispatchToProps = (dispatch) => ({
    handleNameChange: (name) => dispatch(updateEmployeeFormName(name)),
    handleSalaryChange: (salary) => dispatch(updateEmployeeFormSalary(salary)),
    handleButtonClick: (name, salary) => dispatch(createEmployee(name, salary)),
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(EmployeeForm);

