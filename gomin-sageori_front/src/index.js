import React from 'react';
import { createRoot } from 'react-dom/client';
import { Global } from '@emotion/react';
import { BrowserRouter } from 'react-router-dom';
import App from './App';

/** @jsxImportSource @emotion/react */
import reset from './styles/base/global-styles';

const container = document.getElementById('root');
const root = createRoot(container);

root.render(
  <React.StrictMode>
    <Global styles={reset} />
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>,
);
