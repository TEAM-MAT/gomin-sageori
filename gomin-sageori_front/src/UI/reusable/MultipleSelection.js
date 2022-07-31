/** @jsxImportSource @emotion/react */
import { jsx, css } from '@emotion/react';
import { useState, useEffect } from 'react';
import useStore from '../../state/store';
import BasicButton from './BasicButton';

const shortId = require('shortid');

function MultipleSelection(props) {
  const { data, MAX_SELECT, selectionChange } = props;

  const [selectArr, setSelectArr] = useState(Array(data.length).fill(false));
  const [disableArr, setDisableArr] = useState(Array(data.length).fill(false));

  const { isPreferMaxSelect, toggleIsPreferMaxSelect } = useStore();

  const containerStyle = css`
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 5px;
    margin-bottom: 5px;
  `;

  const scrollStyle = css`
    overflow: scroll;
    height: 100vh;
  `;
  const h3Style = css`
    font-size: 0.95em;
    font-weight: 400;
  `;
  const buttonWrapStyle = css`
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: center;
    width: 90vw;
    justify-content: space-evenly;
  `;

  const multipleHandleClick = (idx) => {
    // 3개 초과 클릭 시
    if (
      selectArr.filter((p) => p === true).length >= MAX_SELECT &&
      selectArr[idx] === false
    ) {
      toggleIsPreferMaxSelect(true);
    } else if (
      selectArr.filter((p) => p === true).length >= MAX_SELECT &&
      selectArr[idx] === true
    ) {
      const newArr = [...selectArr];
      newArr[idx] = !newArr[idx];
      setSelectArr(newArr);
      toggleIsPreferMaxSelect(false);
      selectionChange(data[idx][1]);
    } else {
      const newArr = [...selectArr];
      newArr[idx] = !newArr[idx];
      setSelectArr(newArr);
      toggleIsPreferMaxSelect(false);
      selectionChange(data[idx][1]);
    }
  };

  const multipleHandleDisable = (idx) => {
    // 1, 2, 5, 6, 8, 9, 10, 11, 없음(0)
    if (idx === 0 && disableArr[0] === false) {
      const newDisableArr = Array(data.length).fill(true);
      setDisableArr(newDisableArr);
      const newArr = Array(data.length).fill(false);
      newArr[0] = true;
      setSelectArr(newArr);
    } else if (idx === 0 && disableArr[0] === true) {
      // 초기화
      const newArr = Array(data.length).fill(false);
      setSelectArr(newArr);

      const newDisableArr = Array(data.length).fill(false);
      setDisableArr(newDisableArr);
    } else if (disableArr[0] === true) {
      const newArr = Array(data.length).fill(false);
      newArr[idx] = true;
      setSelectArr(newArr);
      const newDisableArr = Array(data.length).fill(false);
      setDisableArr(newDisableArr);
    }
  };

  return (
    <div css={containerStyle}>
      <h3 css={h3Style}>먹고자 하는 음식의 특징을 선택해주세요</h3>
      <div css={buttonWrapStyle}>
        {data.map((elm, index) => (
          <BasicButton
            key={shortId}
            isSelected={selectArr[index]}
            handleClick={multipleHandleClick}
            elementIndex={index}
            content={elm[0]}
            handleDisable={multipleHandleDisable}
            isDisable={disableArr[index]}
          />
        ))}
      </div>
    </div>
  );
}

export default MultipleSelection;
