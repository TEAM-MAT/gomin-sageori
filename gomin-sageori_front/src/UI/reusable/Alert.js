/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import useStore from "../../state/store";
import "../../styles/base/font.css";

function Alert(props) {
  const { isPreferMaxSelect } = useStore();

  const alertStyle = css`
    width: 250px;
    height: 60px;
    position: absolute;
    top: 80px;
    left: 10px;

    background: linear-gradient(
      90deg,
      rgba(247, 163, 79, 0.65) 0%,
      rgba(247, 150, 79, 0.15) 69.7%
    );
    background-color: #fff;
    border-radius: 6px;

    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;

    margin: 5px 0;
  `;

  const iconWrapStyle = css`
    width: 20px;
    height: 20px;
    background-color: #f7964f;
    box-shadow: #f7964f80 0px 0px 10px 5px;
    border-radius: 25px;

    display: flex;
    justify-content: center;
    align-items: center;
  `;

  const crossIcon = css`
    width: 10px;
    height: 10px;

    margin: 0;
  `;

  const msgBox = css`
    display: flex;
    flex-direction: column;
    justify-content: center;

    margin-left: 10px;
  `;

  const alertMainMsg = css`
    color: #f88634;
    font-size: 10px;
    font-weight: bold;

    padding: 1px;
    margin: 1px 0;
  `;

  const alertSubMsg = css`
    color: #696969;
    font-size: 7px;
    font-weight: normal;

    padding: 1px;
    margin: 1px 0;
  `;

  const invisibleElement = css`
    display: none;
  `;

  return (
    <div css={isPreferMaxSelect.isMax ? alertStyle : alertStyle}>
      <span css={iconWrapStyle}>
        <img
          alt="cross"
          css={crossIcon}
          src="https://i.imgur.com/vrG7VD9.png"
        />
      </span>
      <div css={msgBox}>
        <span css={alertMainMsg}>
          선택한 특징이 최대 개수를 초과하였습니다.
        </span>
        <span css={alertSubMsg}>
          다른 특징을 선택하려면 선택한 특징을 해제해주세요.
        </span>
      </div>
    </div>
  );
}

export default Alert;
