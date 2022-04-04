/** @jsxImportSource @emotion/react */
import { jsx, css } from '@emotion/react'
import { useState, useEffect } from 'react';

function BasicButton(props) {
    
    const [Click, setClick] = useState();

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

      cursor: pointer;
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

      cursor: pointer;
    `

    useEffect(() => {
      console.log('yes');
    }, []);

    // const buttonStyle = css`
    //   width: 80px;
    //   height: 25px;
    //   background-color: #FFE9D3;
    //   border-radius: 50px;
    //   font-size: 10px;
      
    //   display: flex;
    //   flex-direction: column;
    //   align-items: center;
    //   justify-content: center;
    // `

    return (
      <div className="BasicButton">
        <div css={Click === false ? buttonStyle : clickedButtonStyle}
             onClick={()=>{
              setClick(!Click)
             }}>
          {props.content}
        </div>
      </div>
    );
  }
  
  export default BasicButton;
  