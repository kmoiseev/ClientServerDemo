import {connect} from 'react-redux'
import EmployeeTable from "../views/EmployeeTable"
import {refreshAllEmployees, updateEmployeeSalary} from "../../actions/composite/EmployeeCompositeActions";
import {getAllEmployeesList} from "../../selectors/EmployeeListSelector";
import {updateEmployeeListSingleEmployeeSalary} from "../../actions/EmployeeListActions";
import withInitializer from "../decorators/Initializer";

const mapStateToProps = (state) => ({
    employees: getAllEmployeesList(state),
    buttonText: "Update",
});

const mapDispatchToProps = (dispatch) => ({
    handleSalaryChangeForEmployee: (id, salary) => dispatch(updateEmployeeListSingleEmployeeSalary(id, salary)),
    handleButtonClickForEmployee: (id, salary) => dispatch(updateEmployeeSalary(id, salary)),
    initializingFunction: () => dispatch(refreshAllEmployees())
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(withInitializer(EmployeeTable));

