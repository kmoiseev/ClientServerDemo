import {connect} from 'react-redux'
import RemoveEmployeesButton from "../views/RemoveEmployeesButton";
import {deleteAllEmployees} from "../../actions/composite/EmployeeCompositeApiActions";
import {isEmployeesListEmpty} from "../../selectors/EmployeeListSelector";
import {isApiInProgress} from "../../selectors/ApiIndicationSelector";

const mapStateToProps = state => ({
    buttonDisabled: isEmployeesListEmpty(state) || isApiInProgress(state),
    buttonText: 'Delete All',
});

const mapDispatchToProps = dispatch => ({
    handleDeleteAll: _ => dispatch(deleteAllEmployees()),
});

export default connect(
    mapStateToProps,
    mapDispatchToProps,
)(RemoveEmployeesButton);

