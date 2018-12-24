import React, { Suspense, lazy } from 'react';

import { StyledRouter } from './styles';

const HomePage = lazy(() => import('../Home'));
const PathPage = lazy(() => import('../Path'));

export const App = () => (
  <Suspense fallback={null}>
    <StyledRouter>
      <HomePage path="/" />
      <PathPage path="path" />
    </StyledRouter>
  </Suspense>
);
