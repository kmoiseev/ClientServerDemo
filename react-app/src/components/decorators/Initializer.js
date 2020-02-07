import * as React from 'react';
import PropTypes from 'prop-types';

function withInitializer(WrappedComponent) {
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

    render() {
      return (
            <WrappedComponent {...this.props} />
      );
    }
  }

  ComponentWithInitializer.propTypes = {
    ...WrappedComponent.propTypes,
    initializingFunction: PropTypes.func.isRequired,
  };

  return ComponentWithInitializer;
}

export default withInitializer;
