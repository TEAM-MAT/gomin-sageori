/** @jsxImportSource @emotion/react */
import { jsx, css } from '@emotion/react'

function BasicButton(props) {
    const buttonNormal = props => (
        <button
            css={{
                width: '80px',
                height: '25px',
                backgroundColor: '#FFE9D3',
                borderRadius: '50px'
            }}
            {...props.content}
        />
    )

    return (
      <div className="BasicButton">
        <buttonNormal content={props.content}></buttonNormal>
      </div>
    );
  }
  
  export default BasicButton;
  