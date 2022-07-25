/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import shortid from 'shortid';
import '../../styles/base/font.css';

const shortId = require('shortid');

function BasicButton(props) {
  const { isSelected, handleClick, elementIndex, handleDisable, isDisable } =
    props;

  const buttonStyle = css`
    width: 80px;
    height: 25px;
    background-color: #ffe9d3;
    border-radius: 50px;
    font-size: 10px;
    border: solid 0.7px transparent;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    margin: 5px 0;
  `;

  const clickedButtonStyle = css`
    width: 80px;
    height: 25px;
    background-color: #ffe9d3;
    border-radius: 50px;
    font-size: 10px;
    color: #f7964f;
    border: solid 0.7px #f58634;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    margin: 5px 0;
  `;

  const buttonStyleTest = css`
    width: 80px;
    height: 25px;
    background-color: #ffe9d3;
    border-radius: 50px;
    font-size: 10px;
    color: red;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    margin: 5px 0;
  `;

  return (
    <div className="BasicButton" key={shortId}>
      {/* if문 처리 수정 */}
      {isDisable && elementIndex !== 0 ? (
        <li
          onClick={() => {
            handleDisable(elementIndex);
          }}
          css={buttonStyle}
        >
          {props.content}
        </li>
      ) : (
        <li
          onClick={() => {
            handleClick(elementIndex);
            handleDisable(elementIndex);
          }}
          css={isSelected ? clickedButtonStyle : buttonStyle}
        >
          {props.content}
        </li>
      )}
    </div>
  );
}

export default BasicButton;
