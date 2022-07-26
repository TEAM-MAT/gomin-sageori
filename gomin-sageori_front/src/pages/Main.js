/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import Intro from "../UI/main/Intro";
import Header from "../UI/main/Header";
import {
  containerColStyle,
  containerRowStyle,
} from "../styles/layout/Container";
import BasicButton from "../UI/reusable/BasicButton";
import ToggleButton from "../UI/reusable/ToggleButton";
import ConfirmButton from "../UI/reusable/ConfirmButton";
import Alert from "../UI/reusable/Alert";
import { useEffect, useState } from "react";
import RecommendList from "../UI/reusable/RecommendList";
import { NavLink } from "react-router-dom";
import useStore from "../state/store";

function Main() {
  const { userSelection, setUserSelection } = useStore();
  const { recommendResult, setRecommendResult } = useStore();
  // const setRecommendResult = useStore((state) => state.setRecommendResult);
  // const recommendResult = useStore((state) => state.recommendResult);

  // const setIsAllSelect = useStore((state) => state.setIsAllSelect(true));
  const { isPreferMaxSelect, toggleIsPreferMaxSelect } = useStore();

  const preferArr = [
    ["없음", ""],
    ["국물이 있는", "soup"],
    ["매운", "spicy"],
    ["달달한", "sweet"],
    ["뜨거운", "hot"],
    ["고기가 있는", "meat"],
    ["면이 있는", "noodle"],
    ["밥이 있는", "rice"],
    ["빵이 있는", "bread"],
  ];

  const regionArr = ["숭입", "설입", "신촌"];

  const preferSelectionChange = (value) => {
    const oldSet = userSelection.prefer;
    if (oldSet.has(value)) {
      oldSet.delete(value);
    } else {
      oldSet.add(value);
    }
    const newForm = {
      ...userSelection,
      prefer: oldSet,
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

  const [isPreferSelect, setIsPreferSelect] = useState(
    Array(preferArr.length).fill(false)
  );
  // const [isAtmosphereSelect, setIsAtmosphereSelect] = useState(
  //   Array(atmosphereArr.length).fill(false)
  // );
  // const [isAllergySelect, setIsAllergySelect] = useState(
  //   Array(allergyArr.length).fill(false)
  // );
  const [isRegionSelect, setIsRegionSelect] = useState(
    Array(regionArr.length).fill(false)
  );

  const [isPreferDisable, setIsPreferDisable] = useState(
    Array(preferArr.length).fill(false)
  );
  // const [isAtmosphereDisable, setIsAtmosphereDisable] = useState(
  //   Array(atmosphereArr.length).fill(false)
  // );
  // const [isAllergyDisable, setIsAllergyDisable] = useState(
  //   Array(allergyArr.length).fill(false)
  // );

  const MAXIMUM_PREFER_SELECT = 3;

  const preferHandleClick = (idx) => {
    //3개 초과 클릭 시
    if (
      isPreferSelect.filter((p) => p === true).length >=
        MAXIMUM_PREFER_SELECT &&
      isPreferSelect[idx] === false
    ) {
      toggleIsPreferMaxSelect(true);
    } else if (
      isPreferSelect.filter((p) => p === true).length >=
        MAXIMUM_PREFER_SELECT &&
      isPreferSelect[idx] === true
    ) {
      const newArr = [...isPreferSelect];
      newArr[idx] = !newArr[idx];
      setIsPreferSelect(newArr);
      toggleIsPreferMaxSelect(false);
      preferSelectionChange(preferArr[idx][1]);
    } else {
      const newArr = [...isPreferSelect];
      newArr[idx] = !newArr[idx];
      setIsPreferSelect(newArr);
      toggleIsPreferMaxSelect(false);
      preferSelectionChange(preferArr[idx][1]);
    }
  };

  const preferHandleDisable = (idx) => {
    // 1, 2, 5, 6, 8, 9, 10, 11, 없음(0)
    if (idx === 0 && isPreferDisable[0] === false) {
      const newDisableArr = Array(preferArr.length).fill(true);
      setIsPreferDisable(newDisableArr);
      const newArr = Array(preferArr.length).fill(false);
      newArr[0] = true;
      setIsPreferSelect(newArr);
    } else if (idx === 0 && isPreferDisable[0] === true) {
      // 초기화
      const newArr = Array(preferArr.length).fill(false);
      setIsPreferSelect(newArr);

      const newDisableArr = Array(preferArr.length).fill(false);
      setIsPreferDisable(newDisableArr);
    } else if (isPreferDisable[0] === true) {
      const newArr = Array(preferArr.length).fill(false);
      newArr[idx] = true;
      setIsPreferSelect(newArr);
      const newDisableArr = Array(preferArr.length).fill(false);
      setIsPreferDisable(newDisableArr);
    }
  };

  // const atmosphereHandleClick = (idx) => {
  //   const newArr = [...isAtmosphereSelect];
  //   newArr[idx] = !newArr[idx];
  //   setIsAtmosphereSelect(newArr);
  //   userSelectionChange("atmosphere", atmosphereArr[idx]);
  // };

  // const atmosphereHandleDisable = (idx) => {
  //   // 1, 2, 5, 6, 8, 9, 10, 11, 없음(0)
  //   if (idx === 0 && isAtmosphereDisable[0] === false) {
  //     const newDisableArr = Array(atmosphereArr.length).fill(true);
  //     setIsAtmosphereDisable(newDisableArr);
  //   } else if (idx === 0 && isAtmosphereDisable[0] === true) {
  //     // 초기화
  //     const newArr = Array(atmosphereArr.length).fill(false);
  //     setIsAtmosphereSelect(newArr);

  //     const newDisableArr = Array(atmosphereArr.length).fill(false);
  //     setIsAtmosphereDisable(newDisableArr);
  //   }
  // };

  // const allergyHandleClick = (idx) => {
  //   const newArr = [...isAllergySelect];
  //   newArr[idx] = !newArr[idx];
  //   setIsAllergySelect(newArr);
  //   userSelectionChange("allergy", allergyArr[idx]);
  // };

  // const allergyHandleDisable = (idx) => {
  //   // 1, 2, 5, 6, 8, 9, 10, 11, 없음(0)
  //   if (idx === 0 && isAllergyDisable[0] === false) {
  //     const newDisableArr = Array(allergyArr.length).fill(true);
  //     setIsAllergyDisable(newDisableArr);
  //   } else if (idx === 0 && isAllergyDisable[0] === true) {
  //     // 초기화
  //     const newArr = Array(allergyArr.length).fill(false);
  //     setIsAllergySelect(newArr);

  //     const newDisableArr = Array(allergyArr.length).fill(false);
  //     setIsAllergyDisable(newDisableArr);
  //   }
  // };

  const regionHandleClick = (idx) => {
    const newRegionArr = Array(regionArr.length).fill(false);
    newRegionArr[idx] = true;
    setIsRegionSelect(newRegionArr);
    regionSelectionChange(regionArr[idx]);
  };

  const notCompleteSelect =
    userSelection.prefer.size < 1 || userSelection.region === "";

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
      {/*<Header></Header>*/}
      <Intro></Intro>
      <div>
        {' '}
        {/*내용 부분 감싸는 div*/}
        <div css={containerStyle}>
          <h3 css={h3Style}>먹고자 하는 음식의 특징을 선택해주세요</h3>
          <div css={buttonWrapStyle}>
            {preferArr.map((elm, index) => {
              return (
                <BasicButton
                  key={index}
                  isSelected={isPreferSelect[index]}
                  handleClick={preferHandleClick}
                  elementIndex={index}
                  content={elm[0]}
                  handleDisable={preferHandleDisable}
                  isDisable={isPreferDisable[index]}
                />
              );
            })}
          </div>
        </div>
        {/* <div css={containerStyle}>
          <h3 css={h3Style}>원하는 분위기를 선택해주세요</h3>
          <div css={buttonWrapStyle}>
            {atmosphereArr.map((elm, index) => {
              return (
                <BasicButton
                  key={index}
                  isSelected={isAtmosphereSelect[index]}
                  handleClick={atmosphereHandleClick}
                  elementIndex={index}
                  content={elm}
                  handleDisable={atmosphereHandleDisable}
                  isDisable={isAtmosphereDisable[index]}
                />
              );
            })}
          </div>
        </div> */}
        {/* <div css={containerStyle}>
          <h3 css={h3Style}>알레르기 정보를 선택해주세요</h3>
          <div css={buttonWrapStyle}>
            {allergyArr.map((elm, index) => {
              return (
                <BasicButton
                  key={index}
                  isSelected={isAllergySelect[index]}
                  handleClick={allergyHandleClick}
                  elementIndex={index}
                  content={elm}
                  handleDisable={allergyHandleDisable}
                  isDisable={isAllergyDisable[index]}
                />
              );
            })}
          </div>
        </div> */}
        <div css={containerStyle}>
          <h3 css={h3Style}>프랜차이즈를 선택 대상에 포함시킬까요?</h3>
          <div css={toggleButtonWrapStyle}>
            <ToggleButton content="포함" toggledContent="미포함"></ToggleButton>
          </div>
        </div>
        <div css={containerStyle}>
          <h3 css={h3Style}>방문하고 싶으신 지역을 선택해주세요</h3>
          <div css={buttonWrapStyle}>
            {regionArr.map((elm, index) => {
              return (
                <BasicButton
                  key={index}
                  isSelected={isRegionSelect[index]}
                  handleClick={regionHandleClick}
                  elementIndex={index}
                  content={elm}
                  handleDisable={() => {}}
                  isDisable={null}
                />
              );
            })}
          </div>
        </div>
        <NavLink to="/recommend">
          <ConfirmButton
            content="찾아보기"
            handleClick={e => {
              if (notCompleteSelect) {
                e.preventDefault();
              } else {
                console.log('실행');
                setRecommendResult(userSelection);
                setUserSelection({
                  prefer: new Set(),
                  atmosphere: '',
                  allergy: '',
                  franchise: false,
                  region: '',
                });
              }
            }}
          />
        </NavLink>
      </div>
      {/* Alert 작성중 */}
      {/* <Alert /> */}
    </div>
  );
}

export default Main;
