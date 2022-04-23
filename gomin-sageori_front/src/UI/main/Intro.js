/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react'

import {containerColStyle} from "../../styles/layout/Container";

function Intro() {
    const introBackStyle = css`
      width: 100vw;
      min-width: 390px;
      height: 300px;
      background-color: #FFF2E9;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
    `

    const h1Style = css`
      text-align: center;
      font-weight: 400;
      margin-bottom: 10px;
    `

    const boldStyle = css`
      font-weight: 600;
    `

    const logoWrapStyle = css`
      width: 120px;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: space-between;
      margin-bottom: 10px;
    `

    return (
        <div css={containerColStyle}>
            <div css={introBackStyle}>
                <div css={logoWrapStyle}>
                    <div>로고</div>
                    <h2>고민사거리</h2>
                </div>
                <h1 css={h1Style}>
                    더 이상의 고민은 없다<br/>
                    이제는 벗어나자<br/>
                    <b css={boldStyle}>고민 사거리</b>
                </h1>
                <p>더 이상 사거리에서 고민하지 말자!</p>
            </div>
        </div>
    );
}

export default Intro;
