import React from "react";
import { createRoot } from "react-dom/client";
import App from "./App";

/** @jsxImportSource @emotion/react */
import { Global } from "@emotion/react";
import reset from "./styles/base/global-styles";

const container = document.getElementById("root");
const root = createRoot(container);

root.render(
  <React.StrictMode>
    <Global styles={reset} />
    <App />
  </React.StrictMode>
);
