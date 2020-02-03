import {connect} from 'react-redux'
import {getEmployeeFormName, getEmployeeFormSalary} from "../../selectors/EmployeeFormSelector";
import EmployeeForm from "../views/EmployeeForm"
import {updateEmployeeFormName, updateEmployeeFormSalary} from "../../actions/EmployeeFormActions";
import {createEmployee} from "../../actions/composite/EmployeeCompositeActions";
import {isInvalid} from "../../selectors/ValidationSelector";
import {getKeyForEmployeeFormName, getKeyForEmployeeFormSalary} from "../../static/ValidationKeyBuilder";
import {markInvalid, markValid} from "../../actions/ValidationActions";

const mapStateToProps = (state) => ({
    name: getEmployeeFormName(state),
    salary: getEmployeeFormSalary(state),
    buttonText: "Create",
    nameInputLabel: "Name",
    salaryInputLabel: "Salary",
    isNameInvalid: isInvalid(state, getKeyForEmployeeFormName()),
    isSalaryInvalid: isInvalid(state, getKeyForEmployeeFormSalary()),
    isButtonDisabled:
        isInvalid(state, getKeyForEmployeeFormName()) ||
        isInvalid(state, getKeyForEmployeeFormSalary()) ||
        !getEmployeeFormName(state).length ||
        !getEmployeeFormSalary(state).length
});

const mapDispatchToProps = (dispatch) => ({
    handleNameChange: (name) => dispatch(updateEmployeeFormName(name)),
    handleSalaryChange: (salary) => dispatch(updateEmployeeFormSalary(salary)),
    handleButtonClick: (name, salary) => dispatch(createEmployee(name, salary)),
    markNameValid: () => dispatch(markValid(getKeyForEmployeeFormName())),
    markNameInvalid: () => dispatch(markInvalid(getKeyForEmployeeFormName())),
    markSalaryValid: () => dispatch(markValid(getKeyForEmployeeFormSalary())),
    markSalaryInvalid: () => dispatch(markInvalid(getKeyForEmployeeFormSalary())),
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(EmployeeForm);

