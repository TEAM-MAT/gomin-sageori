/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import styled from '@emotion/styled';
import { ArrowLongRight } from '@emotion-icons/entypo';

function ConfirmButton({ content, handleClick }) {
  const confirmWrapStyle = css`
    width: 100%;
    position: relative;
  `;
  const buttonStyle = css`
    width: 80px;
    height: 25px;
    position: absolute;
    right: 0;
    margin: 1em 5vw;

    background-color: #f5ad79;
    color: black;
    border-radius: 5px;
    font-size: 0.8em;
    font-weight: 500;
    border: solid 0.7px transparent;

    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;

    cursor: pointer;

    &:hover {
      background-color: #f87d26;
    }
  `;

  const Arrow = styled(ArrowLongRight)`
    height: 10px;
  `;

  return (
    <div css={confirmWrapStyle} onClick={handleClick}>
      <div css={buttonStyle}>
        {content}
        <Arrow />
      </div>
    </div>
  );
}

export default ConfirmButton;
