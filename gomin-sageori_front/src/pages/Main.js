/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import Intro from "../UI/main/Intro";
import Header from "../UI/reusable/Header";
import {
 containerColStyle,
 containerRowStyle,
} from "../styles/layout/Container";
import BasicButton from "../UI/reusable/BasicButton";
import ToggleButton from "../UI/reusable/ToggleButton";
import { useState } from "react";

function Main() {
 const [isCategorySelect, setIsCategorySelect] = useState(false);
 const notPreferArr = ["햄버거", "스테이크", "아이스크림", "어쩔티비"];

 const handleClick = (idx) => {
  const newArr = Array(notPreferArr.length).fill(false);
  newArr[idx] = true;
  setIsCategorySelect(newArr);
 };

 const h3Style = css`
  font-size: 0.95em;
  font-weight: 400;
 `;
 const buttonWrapStyle = css`
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 90vw;
  justify-content: space-between;
 `;
 return (
  <div>
   <Header></Header>
   <Intro></Intro>
   <div>
    {" "}
    {/*내용 부분 감싸는 div*/}
    <div css={containerColStyle}>
     <h3 css={h3Style}>싫어하는 음식 또는 최근에 먹은 음식을 선택해주세요</h3>
     <div css={buttonWrapStyle}>
      {notPreferArr.map((elm, index) => {
       return (
        <BasicButton
         key={index}
         isSelected={isCategorySelect[index]}
         handleClick={handleClick}
         elementIndex={index}
         content={elm}
        />
       );
      })}
     </div>
    </div>
   </div>
  </div>
 );
}

export default Main;
