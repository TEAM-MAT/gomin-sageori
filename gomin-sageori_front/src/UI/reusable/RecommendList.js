/** @jsxImportSource @emotion/react */
// import { css } from '@emotion/react';
import "../../styles/base/font.css";
import { useState, useEffect } from "react";

function RecommendList(props) {
    const [bestMenu, setBestMenu] = useState([]);
    useEffect( () => {
        const axios = require("axios");
        axios
            .get("/api/restaurant" + props.id)
            .then((response) => {
                console.log(response.data);
            });
    }, [])
  return (
      <div className="RecommendList">
          <div>
              {/*image*/}
          </div>
          <div>
              {bestMenu.map((key, index) => {
                  <div>{key}</div>
              })}
          </div>
      </div>
  );
}

export default RecommendList;
