/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import Intro from '../UI/main/Intro';
import Header from '../UI/main/Header';
import {
  containerColStyle,
  containerRowStyle,
} from '../styles/layout/Container';
import BasicButton from '../UI/reusable/BasicButton';
import ToggleButton from '../UI/reusable/ToggleButton';
import ConfirmButton from '../UI/reusable/ConfirmButton';
import Alert from '../UI/reusable/Alert';
import RecommendList from '../UI/reusable/RecommendList';
import useStore from '../state/store';
import MultipleSelection from '../UI/reusable/MultipleSelection';

const shortId = require('shortid');

function Main() {
  const { userSelection, setUserSelection } = useStore();
  const { recommendResult, setRecommendResult } = useStore();
  const { isPreferMaxSelect, toggleIsPreferMaxSelect } = useStore();

  const preferArr = [
    ['없음', 'none'],
    ['국물이 있는', 'soup'],
    ['매운', 'spicy'],
    ['달달한', 'sweet'],
    ['뜨거운', 'hot'],
    ['고기가 있는', 'meat'],
    ['면이 있는', 'noodle'],
    ['밥이 있는', 'rice'],
    ['빵이 있는', 'bread'],
  ];

  const regionArr = ['숭입', '설입', '신촌'];

  const selectionSetChange = (value, subject) => {
    const oldSet = userSelection.prefer;
    if (oldSet.has(value)) {
      oldSet.delete(value);
    } else {
      oldSet.add(value);
    }
    const newForm = {
      ...userSelection,
      subject: oldSet,
    };
    setUserSelection(newForm);
  };

  const regionSelectionChange = (value) => {
    const newForm = {
      ...userSelection,
      region: value,
    };
    setUserSelection(newForm);
  };

  const [isRegionSelect, setIsRegionSelect] = useState(
    Array(regionArr.length).fill(false),
  );

  const regionHandleClick = (idx) => {
    const newRegionArr = Array(regionArr.length).fill(false);
    newRegionArr[idx] = true;
    setIsRegionSelect(newRegionArr);
    regionSelectionChange(regionArr[idx]);
  };

  const notCompleteSelect =
    userSelection.prefer.size < 1 || userSelection.region === '';

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

  const toggleButtonWrapStyle = css`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 90vw;
  `;

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

  return (
    <div css={scrollStyle}>
      <Intro />
      <div>
        <MultipleSelection
          subject="prefer"
          data={preferArr}
          MAX_SELECT={3}
          selectionChange={selectionSetChange}
        />
        <div css={containerStyle}>
          <h3 css={h3Style}>프랜차이즈를 선택 대상에 포함시킬까요?</h3>
          <div css={toggleButtonWrapStyle}>
            <ToggleButton content="포함" toggledContent="미포함" />
          </div>
        </div>
        <div css={containerStyle}>
          <h3 css={h3Style}>방문하고 싶으신 지역을 선택해주세요</h3>
          <div css={buttonWrapStyle}>
            {regionArr.map((elm, index) => (
              <BasicButton
                key=""
                isSelected={isRegionSelect[index]}
                handleClick={regionHandleClick}
                elementIndex={index}
                content={elm}
                handleDisable={() => {}}
                isDisable={null}
              />
            ))}
          </div>
        </div>
        <NavLink to="/recommend">
          <ConfirmButton
            content="찾아보기"
            handleClick={(e) => {
              if (notCompleteSelect) {
                e.preventDefault();
              } else {
                setRecommendResult(userSelection);
                console.log(userSelection);
                const preferSelectionArr = Array.from(userSelection.prefer);
                localStorage.setItem('preferSelection', JSON.stringify(preferSelectionArr));
                localStorage.setItem('userSelection', JSON.stringify(userSelection));
              }
            }}
          />
        </NavLink>
      </div>
    </div>
  );
}

export default Main;
