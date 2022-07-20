/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import '../../styles/base/font.css';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

import timeIcon from '../../logo/time.png';
import locationIcon from '../../logo/location.png';
import phoneIcon from '../../logo/phone.png';
import starIcon from '../../logo/star.png';

function RecommendList(props) {
  const [bestMenu, setBestMenu] = useState('');
  const [address, setAddress] = useState('');
  const [callNum, setCallNum] = useState('');
  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');
  const [externalStar, setExternalStar] = useState('');
  const [naverURL, setNaverURL] = useState('');

  useEffect(() => {
    const axios = require('axios');
    axios.get(`/api/restaurant/${props.id}`).then(response => {
      console.log(response.data);
      setBestMenu(response.data.bestMenu);
      // 주소 수정 필요
      setAddress(
        response.data.address.floor == '층 정보 없음'
          ? `${response.data.address.city} ${response.data.address.district} ${response.data.address.road}`
          : `${response.data.address.city} ${response.data.address.district} ${response.data.address.road} ${response.data.address.floor}`,
      );
      setCallNum(response.data.callNumber);
      setStartTime(response.data.startTime);
      setEndTime(response.data.finTime);
      setExternalStar(response.data.externalStar);
      setNaverURL(response.data.naverMapUrl);
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
    flex-direction: row;
    justify-content: space-between;
    margin-top: 15px;
    margin-bottom: 15px;
  `;

  const imageStyle = css`
    width: 120px;
    height: 120px;
    background-color: black;
  `;

  const textWrap = css`
    width: 60%;
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
    font-size: 0.7em;
  `;

  const infoStyle = css`
    color: #656565;
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

  const openStyle = css`
    color: #23b416;
  `;

  const closeStyle = css`
    color: #ff2f2f;
  `;

  return (
    <div
      css={wrapStyle}
      className="RecommendList"
      onClick={() => window.open(naverURL, '_blank')}
    >
      <div css={imageStyle}>{/* image */}</div>
      <div css={textWrap}>
        <div>{props.name}</div>
        <div>
          <div css={bestMenuStyle}>{bestMenu}</div>
        </div>
        <div css={infoWrapStyle}>
          <div css={infoDetailWrap}>
            <div css={locationIconStyle} />
            <div css={infoStyle}>{address}</div>
          </div>
          <div css={infoDetailWrap}>
            <div css={phoneIconStyle} />
            <div css={infoStyle}>
              {callNum == '전화번호 없음' ? '등록된 번호가 없습니다.' : callNum}
            </div>
          </div>
          <div css={infoDetailWrap}>
            <div css={timeIconStyle} />
            <div css={infoStyle}>
              {`${startTime.slice(0, 5)} ~ ${endTime.slice(0, 5)}`}
            </div>
          </div>
          <div css={infoDetailWrap}>
            <div
              css={
                checkTime(startTime, endTime) == '영업중'
                  ? openStyle
                  : closeStyle
              }
            >
              {checkTime(startTime, endTime)}
            </div>
            <div css={starStyle} />
            <div>{externalStar}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default RecommendList;
