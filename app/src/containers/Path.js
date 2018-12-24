import React, { PureComponent } from 'react';

import { Nav, PathSpots } from '../components';

export default class Path extends PureComponent {
  state = this.props.location.state;

  render () {
    const { startPlace, endPlace, move, path, time } = this.state;

    return (
      <>
        <Nav startPlace={startPlace} endPlace={endPlace} />
        <PathSpots move={move} path={path} time={time} />
      </>
    );
  }
}
