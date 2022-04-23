/** @jsxImportSource @emotion/react */
import { jsx, css } from '@emotion/react'
import { useState, useEffect } from 'react';

function BasicButton(props) {

    const [Toggle, setToggle] = useState();

    const buttonBGStyle = css`
      width: 168px;
      height: 39px;
      background: #FFF7F1;
      font-size: 15px;
      color: #F7964F;
      box-shadow: inset 2px 2px 10px rgba(0, 0, 0, 0.1);
      border-radius: 50px;

      display: flex;
      flex-direction: column;
      justify-content: center;
    `

    const toggledButtonStyle = css`
      width: 95px;
      height: 39px;
      background: #FFE6D5;
      font-size: 15px;
      color: #F7964F;
      border: 0.7px solid #F7964F;
      border-radius: 50px;

      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      
      transform: translateX(82px);
      transition: all .2s ease;

      cursor: pointer;
    `

    const buttonStyle = css`
      width: 95px;
      height: 39px;

      background: #FFE6D5;
      border: 0.7px solid #F7964F;
      border-radius: 50px;
      
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;

      transform: translateX(0px);
      transition: all .2s ease;
      
      cursor: pointer;
    `

    useEffect(() => {
        console.log('yes');
    }, []);

    return (
        <div className="BasicButton">
            <div css={buttonBGStyle}>
                <div css={Toggle === false ? buttonStyle : toggledButtonStyle}
                     onClick={()=>{
                         setToggle(!Toggle)
                     }}>
                    {Toggle === false ? props.content : props.toggledContent}
                </div>
            </div>
        </div>
    );
}

export default BasicButton;
