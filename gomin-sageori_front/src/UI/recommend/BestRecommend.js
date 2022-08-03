/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import '../../styles/base/font.css';
import { useState, useEffect } from 'react';

import timeIcon from '../../logo/time.png';
import locationIcon from '../../logo/location.png';
import phoneIcon from '../../logo/phone.png';
import starIcon from '../../logo/star.png';

const axios = require('axios');

function BestRecommend(props) {
  const { id, name } = props;
  const [states, setStates] = useState({
    name: '',
    bestMenu: '',
    address: '',
    callNum: '',
    startTime: '',
    endTime: '',
    externalStar: '',
    naverURL: '',
  });

  const stateHandler = (stateName, stateVal) => {
    setStates(prevState => {
      return {
        ...prevState,
        [stateName]: stateVal,
      };
    });
  };
  useEffect(() => {
    axios.get(`/api/restaurant/${id}`).then(response => {
      stateHandler('bestMenu', response.data.bestMenu);
      stateHandler(
        'address',
        response.data.address.floor === '층 정보 없음'
          ? `${response.data.address.city} ${response.data.address.district} ${response.data.address.road}`
          : `${response.data.address.city} ${response.data.address.district} ${response.data.address.road} ${response.data.address.floor}`,
      );
      stateHandler('callNum', response.data.callNumber);
      stateHandler('startTime', response.data.startTime);
      stateHandler('endTime', response.data.finTime);
      stateHandler('externalStar', response.data.externalStar);
      stateHandler('naverURL', response.data.naverMapURL);
    });
  }, []);

  const checkTime = (start, end) => {
    // 현재 시간
    const today = new Date();

    const hours = today.getHours();
    const minutes = today.getMinutes();

    const startHours = Number(start.slice(0, 2));

    const endHours = Number(end.slice(0, 2));
    const endMinutes = Number(end.slice(3, 5));

    if (startHours <= hours && hours <= endHours && minutes < endMinutes) {
      return '영업중';
    }
    return '영업 종료';
  };

  const wrapStyle = css`
    width: 90vw;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    margin-top: 15px;
    margin-bottom: 15px;
  `;

  const imageStyle = css`
    width: 200px;
    height: 200px;
    background-color: black;
  `;

  const titleWrapStyle = css`
    display: flex;
    flex-direction: row;
    font-size: 1.5em;
    margin: 10px 0px;
  `;

  const startWrapStyle = css`
    display: flex;
    flex-direction: row;
    align-items: center;
    font-size: 0.4em;
  `;

  const starStyle = css`
    width: 12px;
    height: 12px;
    background-image: url(${starIcon});
    background-position: center;
    background-size: contain;
    background-repeat: no-repeat;
    margin-left: 3px;
    margin-right: 3px;
  `;

  const textWrap = css`
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
  `;

  const bestMenuStyle = css`
    display: flex;
    justify-content: center;
    align-items: center;

    font-size: 0.7em;
    font-weight: 300;
    color: #474747;

    width: 90px;
    height: 22px;
    background-color: #ffe1cb;
    border-radius: 50px;

    margin-top: 5px;
    margin-bottom: 5px;
  `;

  const infoWrapStyle = css`
    width: 100%;
    font-size: 0.9em;
    color: #656565;
  `;

  const infoTitleStyle = css`
    width: 100%;
  `;

  const infoStyle = css`
    margin-top: 3px;
    margin-bottom: 3px;
    margin-left: 5px;
  `;

  const infoDetailWrap = css`
    display: flex;
    flex-direction: row;
    align-items: center;
  `;

  const timeIconStyle = css`
    width: 12px;
    height: 12px;
    background-image: url(${timeIcon});
    background-position: center;
    background-size: contain;
    background-repeat: no-repeat;
  `;

  const phoneIconStyle = css`
    width: 12px;
    height: 12px;
    background-image: url(${phoneIcon});
    background-position: center;
    background-size: contain;
    background-repeat: no-repeat;
  `;

  const locationIconStyle = css`
    width: 12px;
    height: 12px;
    background-image: url(${locationIcon});
    background-position: center;
    background-size: contain;
    background-repeat: no-repeat;
  `;

  const openStyle = css`
    font-size: 0.4em;
    color: #23b416;
    margin-left: 4px;
  `;

  const closeStyle = css`
    font-size: 0.4em;
    color: #ff2f2f;
    margin-left: 4px;
  `;
  return (
    <div
      css={wrapStyle}
      className="RecommendList"
      onClick={() => window.open(states.naverURL, '_blank')}
      onKeyPress={() => {}}
      role="button"
      tabIndex="0"
    >
      <div css={imageStyle}>
        <img
          alt={name}
          src={`https://gomin-image.s3.ap-northeast-2.amazonaws.com/restaurant-images/${id}_1.jpg`}
        />
      </div>
      <div css={textWrap}>
        <div css={titleWrapStyle}>
          <div>{name}</div>
          <div css={startWrapStyle}>
            <div css={starStyle} />
            <div>{states.externalStar}</div>
          </div>
        </div>
        <div>
          <div css={bestMenuStyle}>{states.bestMenu}</div>
        </div>
        <div css={infoWrapStyle}>
          <div css={infoTitleStyle}>
            가게정보
            <hr />
          </div>
          <div css={infoDetailWrap}>
            <div css={locationIconStyle} />
            <div css={infoStyle}>{states.address}</div>
          </div>
          <div css={infoDetailWrap}>
            <div css={phoneIconStyle} />
            <div css={infoStyle}>
              {states.callNum === '전화번호 없음'
                ? '등록된 번호가 없습니다.'
                : states.callNum}
            </div>
          </div>
          <div css={infoDetailWrap}>
            <div css={timeIconStyle} />
            <div css={infoStyle}>
              {`${states.startTime.slice(0, 5)} ~ ${states.endTime.slice(0, 5)}`}
            </div>
            <div
              css={
                checkTime(states.startTime, states.endTime) === '영업중'
                  ? openStyle
                  : closeStyle
              }
            >
              {checkTime(states.startTime, states.endTime)}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default BestRecommend;
