/** @jsxImportSource @emotion/react */
import { jsx, css } from "@emotion/react";

function Intro() {
 const divStyle = css`
  height: 264px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;

  background-color: #ffe6d5;
 `;

 const titleStyle = css``;

 return (
  <div css={divStyle} id="Intro">
   <h2 css={titleStyle}>고민사거리</h2>
   <p>
    더 이상의 고민은 없다
    <br />
    이제는 벗어나자
    <br />
    고민사거리
   </p>
   <p>더 이상 사거리에서 고민하지 말자!</p>
  </div>
 );
}

export default Intro;
