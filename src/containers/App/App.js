import React, { Component, Suspense, lazy } from 'react';

import { StyledRouter } from './styles';

const HomePage = lazy(() => import('../Home'));

export class App extends Component {
  render() {
    return (
      <Suspense fallback={null}>
        <StyledRouter>
          <HomePage path="/" />
        </StyledRouter>
      </Suspense>
    );
  }
}
