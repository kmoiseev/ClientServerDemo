import * as React from "react";
import PropTypes from 'prop-types';

const withInitializer = (WrappedComponent) => {
    class ComponentWithInitializer extends React.Component {

        constructor(props) {
            super(props);

            this.state = {
                initialized: false,
            };
        }

        componentDidMount() {
            this.props.initializingFunction();
        }

        render = () => (
            <WrappedComponent {...this.props} />
        );
    }

    ComponentWithInitializer.propTypes = {
        ...WrappedComponent.propTypes,
        initializingFunction: PropTypes.func.isRequired,
    };

    return ComponentWithInitializer;
};

export default withInitializer;