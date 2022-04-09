/** @jsxImportSource @emotion/react */
import { jsx, css } from "@emotion/react";
import { useEffect } from "react";
import styled from "@emotion/styled";
import { ArrowLongRight } from "@emotion-icons/entypo";

function BasicButton(props) {
 const buttonStyle = css`
  width: 80px;
  height: 25px;
  background-color: #34a3f5;
  border-radius: 5px;
  font-size: 10px;
  border: solid 0.7px transparent;

  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  cursor: pointer;

  &:hover {
   border: solid 0.7px #3443f5;
  }
 `;

 const Arrow = styled(ArrowLongRight)`
  height: 10px;
 `;

 useEffect(() => {
  console.log("yes");
 }, []);

 return (
  <div className="ConfirmButton">
   <div css={buttonStyle}>
    <Arrow />
    {props.content}
   </div>
  </div>
 );
}

export default BasicButton;
