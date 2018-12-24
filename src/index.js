import './index.css';
// Import * as serviceWorker from './serviceWorker';
import React, { StrictMode } from 'react';
import { unstable_createRoot as createRoot } from 'react-dom';

import { App } from './containers';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>
);

/*
 * If you want your app to work offline and load faster, you can change
 * unregister() to register() below. Note this comes with some pitfalls.
 * Learn more about service workers: http://bit.ly/CRA-PWA
 * serviceWorker.unregister();
 */
