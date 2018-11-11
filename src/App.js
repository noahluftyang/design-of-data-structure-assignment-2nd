import { Router } from '@reach/router';
import React, { Component, Suspense, lazy } from 'react';

const HomePage = lazy(() => import('./Home'));

export class App extends Component {
  render() {
    return (
      <Suspense fallback={null}>
        <Router>
          <HomePage path="/" />
        </Router>
      </Suspense>
    );
  }
}
