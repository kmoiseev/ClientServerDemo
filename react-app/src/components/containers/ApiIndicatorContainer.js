import {connect} from 'react-redux'
import ApiIndicator from "../views/ApiIndicator";
import {isApiFailureInProgress, isApiInProgress, isApiSuccessInProgress} from "../../selectors/ApiIndicationSelector";

const mapStateToProps = (state) => ({
    visible:
        isApiInProgress(state) ||
        isApiSuccessInProgress(state) ||
        isApiFailureInProgress(state),
    inProgress: isApiInProgress(state),
    success: isApiSuccessInProgress(state),
    failure: isApiFailureInProgress(state),
});

export default connect(
    mapStateToProps
)(ApiIndicator);

