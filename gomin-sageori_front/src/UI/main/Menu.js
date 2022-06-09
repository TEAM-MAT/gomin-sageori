/** @jsxImportSource @emotion/react */
import { jsx, css } from "@emotion/react";
import styled from "@emotion/styled";
import { Menu } from "@emotion-icons/boxicons-regular";
import { PersonCircle } from "@emotion-icons/bootstrap/";
import { useState } from "react";

function Menu() {
  const [isMenuSelect, setIsMenuSelect] = useState(false);
  const menuStyle = css`
    background-color: white;

    width: 150px;
    height: 356px;
    position: absolute;

    left: -150px;
    box-shadow: 0;
    transition: left 1s;
  `;
  const selectedMenuStyle = css`
    background-color: white;

    width: 150px;
    height: 356px;
    position: absolute;

    left: 0px;
    box-shadow: #f7964f80 3px 5px 10px 5px;
    transition: all 1s;
  `;

  const handleMenuClick = () => {
    setIsMenuSelect(!isMenuSelect);
  };

  const loginDivStyle = css`
    width: 150px;
    height: 56px;
    background-color: #fff;
  `;
  const SampleProfileImage = styled(PersonCircle)`
    height: 35px;
    margin: 1em;
  `;

  const MenuIcon = styled(Menu)`
    width: 25px;
    position: absolute;
    left: 140px;
    top: 16.5px;

    background-color: white;
    box-shadow: #f7964f80 0px 0px 10px 5px;
    border-radius: 25px;

    margin: 0 0 0 13px;
  `;

  const menuListStyle = css`
    height: 300px;

    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: flex-end;
    & > p {
      margin: 0 1em 0 0;
    }
  `;

  return (
    <div css={isMenuSelect ? selectedMenuStyle : menuStyle} id="Menu">
      <MenuIcon onClick={handleMenuClick} />
      <div css={loginDivStyle} id="login">
        <SampleProfileImage />
      </div>
      <div css={menuListStyle}>
        <p>첫번째 메뉴</p>
        <p>두번째 메뉴</p>
        <p>세번째 메뉴</p>
        <p>마지막 메뉴</p>
      </div>
    </div>
  );
}

export default Menu;
