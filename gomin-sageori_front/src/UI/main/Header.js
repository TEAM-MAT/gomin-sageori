/** @jsxImportSource @emotion/react */
import { jsx, css } from "@emotion/react";
import Menu from "./Menu";

function Header() {
 const divStyle = css`
  height: 56px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;

  background-color: #fff;
 `;

 const titleStyle = css`
  margin: 0 0 0 31px;

  display: flex;
  flex-direction: column;
  justify-content: center;

  cursor: default;
  font-weight: bold;
  font-size: 18px;
 `;

 return (
  <div css={divStyle} id="Header">
   <Menu />
   <h1 css={titleStyle}>GO-SA</h1>
  </div>
 );
}

export default Header;
