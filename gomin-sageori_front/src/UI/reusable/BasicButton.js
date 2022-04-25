/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react'
import '../../styles/base/font.css'

function BasicButton(props) {
    const { key, isSelected, handleClick, elementIndex, isDisable } = props;

    const buttonStyle = css`
      width: 80px;
      height: 25px;
      background-color: #FFE9D3;
      border-radius: 50px;
      font-size: 10px;
      border: solid 0.7px transparent;
      
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      
      margin: 5px 0;
    `

    const clickedButtonStyle = css`
      width: 80px;
      height: 25px;
      background-color: #FFE9D3;
      border-radius: 50px;
      font-size: 10px;
      color: #F7964F;
      border: solid 0.7px #F58634;
      
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;

      margin: 5px 0;
    `


    return (
      <div className="BasicButton">
        {/*if문 처리 수정*/}
        {isDisable ?
            <li
                css={buttonStyle}
            >
                {props.content}
            </li>
        :
            <li
                onClick={() => handleClick(elementIndex)}
                css={isSelected ? clickedButtonStyle : buttonStyle}
            >
                {props.content}
            </li>
          }
      </div>
    );
  }
  
  export default BasicButton;
  