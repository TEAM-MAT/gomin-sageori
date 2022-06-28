/** @jsxImportSource @emotion/react */
// import { css } from '@emotion/react';
import "../../styles/base/font.css";
import { useState, useEffect } from "react";

function RecommendList(userSelection, isAllSelect) {
  //식당 추천정보 조회
  const [recommendResult, setRecommendResult] = useState([]);
  useEffect(() => {
    if (isAllSelect) {
      console.log("!!");
      const axios = require("axios");
      axios
        .get("/api/recommendation", {
          params: {
            characteristic: "spicy,noodle",
            franchise: false,
            location: "신촌",
          },
        })
        .then((response) => {
          console.log(response.data.restaurants);
          setRecommendResult(response.data.restaurants);
        });
    } else {
      <div className="RecommendList"></div>;
    }
  }, [isAllSelect]);
  return (
    <div className="RecommendList">
      {recommendResult.map((index, r) => (
        <span key={index}>{r.address.city}</span>
      ))}
    </div>
  );
}

export default RecommendList;
