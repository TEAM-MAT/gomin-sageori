/** @jsxImportSource @emotion/react */
// import { css } from '@emotion/react';
import '../../styles/base/font.css';

function RecommendList(props) {
  const axios = require('axios');
  //식당 추천정보 조회
  var response = axios
    .get('/recommendation', {
      params: {
        characteristic: 'soup',
        franchise: false,
        location: '숭입',
      },
    })
    .then(response => {
      console.log(response);
      return response;
    });
  console.log(props.data, response);

  return (
    <div className="RecommendList">
      {/* {props.data.map((p, index) => (
        <span>{p}</span>
      ))} */}
    </div>
  );
}

export default RecommendList;
