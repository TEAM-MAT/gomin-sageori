/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import "../../styles/base/font.css";

function RecommendList(props) {
 const axios = require("axios");
 //식당 추천정보 조회
 axios
  .get("/api/recommendation", {
   params: {
    characteristic: "soup",
    franchise: false,
    location: "상도",
   },
  })
  .then((response) => console.log(response))
  .catch((e) => console.error(e))
  .then(() => console.log("Load Completed"));

 return <div className="RecommendList"></div>;
}

export default RecommendList;
