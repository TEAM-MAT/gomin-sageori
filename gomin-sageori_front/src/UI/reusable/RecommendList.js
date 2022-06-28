/** @jsxImportSource @emotion/react */
// import { css } from '@emotion/react';
import '../../styles/base/font.css';

function RecommendList(props) {
    const axios = require('axios');
    //식당 추천정보 조회
    var response = axios
        .get('/api/recommendation', {
            params: {
                characteristic: 'spicy,noodle',
                franchise: false,
                location: '신촌',
            },
        })
        .then(response => {
            console.log(response);
            return response;
        });
    console.log(props.data, response);

    return ( <
        div className = "RecommendList" > {
            /* {props.data.map((p, index) => (
                    <span>{p}</span>
                  ))} */
        } <
        /div>
    );
}

export default RecommendList;