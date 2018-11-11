import './index.css';
import * as serviceWorker from './serviceWorker';
import React, { StrictMode } from 'react';
import { App } from './App';
import { unstable_createRoot as createRoot } from 'react-dom';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();