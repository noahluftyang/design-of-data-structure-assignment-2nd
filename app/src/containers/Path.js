import React, { PureComponent } from 'react';

import { Nav, PathSpots } from '../components';

export default class Path extends PureComponent {
  state = this.props.location.state;

  render() {
    const { startLabel, endLabel, move, path, time } = this.state;

    return (
      <>
        <Nav startLabel={startLabel} endLabel={endLabel} />
        <PathSpots move={move} path={path} time={time} />
      </>
    );
  }
}
