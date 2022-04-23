import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

/** @jsxImportSource @emotion/react */
import { Global } from '@emotion/react';
import reset from './styles/base/global-styles';

ReactDOM.render(
  <React.StrictMode>
    <Global styles={reset}/>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

