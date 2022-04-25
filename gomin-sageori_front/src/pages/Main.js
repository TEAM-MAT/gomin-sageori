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
import {useEffect, useState} from "react";


function Main() {
    const preferArr =[ '없음', '국물이 있는', '국물이 없는', '매운', '달달한', '뜨거운', '차가운', '아이스크림', '고기가 있는', '고기가 없는', '술이 있는', '술이 없는', '면이 있는', '밥이 있는', '빵이 있는'];
    const atmosphereArr =['시끌벅적한', '조용한', '혼밥하기 좋은', '친구와 가기 좋은', '없음'];
    const allergyArr =['알레르기 없음', '매밀', '밀', '대두', '땅콩', '호두', '잣', '아황산류', '복숭아', '토마토', '난류', '우유', '새우', '고등어', '오징어', '게', '조개류', '돼지고기', '쇠고기', '닭고기'];
    const regionArr = ['숭실대입구', '서울대입구', '신촌'];

    const [isPreferSelect, setIsPreferSelect] = useState(Array(preferArr.length).fill(false));
    const [isAtmosphereSelect, setIsAtmosphereSelect] = useState( Array(atmosphereArr.length).fill(false));
    const [isAllergySelect, setIsAllergySelect] = useState(Array(allergyArr.length).fill(false));
    const [isRegionSelect, setIsRegionSelect] = useState(Array(regionArr.length).fill(false));

    const [isPreferDisable, setIsPreferDisable] = useState(Array(preferArr.length).fill(false));

    const preferHandleClick = (idx) => {
        const newArr = [...isPreferSelect];
        // 1, 2, 5, 6, 8, 9, 10, 11, 없음(0)
        newArr[idx] = !(newArr[idx]);
        // index === 0안 경우 처리 마저 해야함!!
        if (idx === 0) {
            const newDisableArr = Array(preferArr.length).fill(true);
            newDisableArr[0] = false;
            setIsPreferDisable(newDisableArr);
        }

        setIsPreferSelect(newArr);
    };

    const atmosphereHandleClick = (idx) => {
        const newArr = [...isAtmosphereSelect];
        newArr[idx] = !(newArr[idx]);
        setIsAtmosphereSelect(newArr);
    };

    const allergyHandleClick = (idx) => {
        const newArr = [...isAllergySelect];
        newArr[idx] = !(newArr[idx]);
        setIsAllergySelect(newArr);
    };

    const regionHandleClick = (idx) => {
        const newRegionArr = Array(regionArr.length).fill(false);
        newRegionArr[idx] = true;
        setIsRegionSelect(newRegionArr);
    };

    const h3Style = css`
      font-size: 0.95em;
      font-weight: 400;
    `
    const buttonWrapStyle = css`
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      align-items: center;
      width: 90vw;
      justify-content: space-evenly;
    `

    const toggleButtonWrapStyle = css`
      display: flex;
      justify-content: center;
      align-items: center;
      width: 90vw;
    `

    const containerStyle = css`
      display: flex;
      flex-direction: column;
      align-items: center;
      margin-top: 5px;
      margin-bottom: 5px;
    `

    const scrollStyle = css`
      overflow: scroll;
    `
    return (
      <div css={scrollStyle}>
          {/*<Header></Header>*/}
          <Intro></Intro>
          <div> {/*내용 부분 감싸는 div*/}
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
                                  content={elm}
                                  isDiable={isPreferDisable[index]}
                              />
                          );
                      })}
                  </div>
              </div>
              <div css={containerStyle}>
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
                              />
                          );
                      })}
                  </div>
              </div>
              <div css={containerStyle}>
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
                              />
                          );
                      })}
                  </div>
              </div>
              <div css={containerStyle}>
                  <h3 css={h3Style}>프랜차이즈를 선택 대상에 포함시킬까요?</h3>
                  <div css={toggleButtonWrapStyle}>
                      <ToggleButton content='포함' toggledContent='미포함'></ToggleButton>
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
                              />
                          );
                      })}
                  </div>
              </div>
          </div>
      </div>
    );
  }

export default Main;
