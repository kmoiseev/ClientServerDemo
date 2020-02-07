import { connect } from 'react-redux';
import EmployeeTable from '../views/EmployeeTable';
import { refreshAllEmployees, updateEmployeeSalary } from '../../actions/composite/EmployeeCompositeApiActions';
import { getEmployeesList } from '../../selectors/EmployeeListSelector';
import { updateEmployeeListSingleEmployeeSalary } from '../../actions/EmployeeListActions';
import withInitializer from '../decorators/Initializer';
import { isInvalid } from '../../selectors/ValidationSelector';
import { getKeyForEmployeeList } from '../../static/ValidationKeyBuilder';
import { markInvalid, markValid } from '../../actions/ValidationActions';
import { isApiInProgress } from '../../selectors/ApiIndicationSelector';

const mapStateToProps = (state) => ({
  employees: getEmployeesList(state),
  buttonText: 'Update',
  isUpdateButtonDisabled:
      (employeeId) => isApiInProgress(state) || isInvalid(state, getKeyForEmployeeList(employeeId)),
  isSalaryInvalid:
      (employeeId) => isInvalid(state, getKeyForEmployeeList(employeeId)),
});

const mapDispatchToProps = (dispatch) => ({
  handleSalaryChangeForEmployee:
      (id, salary) => dispatch(updateEmployeeListSingleEmployeeSalary(id, salary)),
  handleButtonClickForEmployee:
      (id, salary) => dispatch(updateEmployeeSalary(id, salary)),
  initializingFunction:
      () => dispatch(refreshAllEmployees()),
  markSalaryInvalid:
      (employeeId) => dispatch(markInvalid(getKeyForEmployeeList(employeeId))),
  markSalaryValid:
      (employeeId) => dispatch(markValid(getKeyForEmployeeList(employeeId))),
});

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(withInitializer(EmployeeTable));
