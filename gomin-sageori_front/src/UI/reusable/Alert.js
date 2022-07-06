/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import "../../styles/base/font.css";
import { Cross } from "@emotion-icons/entypo";

function Alert(props) {
  const alertStyle = css`
    width: 300px;
    height: 90px;
    background: linear-gradient(
      90deg,
      rgba(247, 150, 79, 0.65) 0%,
      rgba(247, 150, 79, 0.15) 18.17%
    );
    border-radius: 15px;
    border: solid 0.7px transparent;

    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;

    margin: 5px 0;
  `;

  const CrossIcon = styled(Cross)`
    width: 50px;
    // position: absolute;
    // left: 140px;
    // top: 16.5px;

    background-color: white;
    box-shadow: #f7964f80 0px 0px 10px 5px;
    border-radius: 25px;

    margin: 0 0 0 13px;
  `;

  const flexColumn = css`
    display: flex;
    flex-direction: column;
    justify-content: center;
  `;

  return (
    <div css={alertStyle}>
      <CrossIcon />
      <div css={flexColumn}>
        <span>선택한 특징이 최대 개수를 초과하였습니다.</span>
        <span>다른 특징을 선택하려면 이미 선택한 특징을 해제해주세요.</span>
      </div>
    </div>
  );
}

export default Alert;
