/** @jsxImportSource @emotion/react */
import { jsx, css } from "@emotion/react";
import styled from "@emotion/styled";
import { MenuAltLeft } from "@emotion-icons/boxicons-regular";

function Header() {
 const divStyle = css`
  height: 56px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;

  background-color: #fff;
 `;

 const MenuIcon = styled(MenuAltLeft)`
  height: 56px;
 `;

 const titleStyle = css`
  margin: 0;

  display: flex;
  flex-direction: column;
  justify-content: center;

  cursor: default;
  text-weight: bold;
 `;

 return (
  <div css={divStyle} id="Header">
   <MenuIcon />
   <h1 css={titleStyle}>GO-SA</h1>
  </div>
 );
}

export default Header;
