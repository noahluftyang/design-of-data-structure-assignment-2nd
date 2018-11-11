import { Router } from '@reach/router';
import React, { Component, Suspense, lazy } from 'react';

import { Nav } from './components/Nav';

const HomePage = lazy(() => import('./Home'));

export class App extends Component {
  render() {
    return (
      <Suspense fallback={null}>
        <Router>
          <HomePage path="/" />
        </Router>
        <Nav />
      </Suspense>
    );
  }
}
