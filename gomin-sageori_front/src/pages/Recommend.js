/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import useStore from "../state/store";
import {useEffect, useState} from "react";
import RecommendList from "../UI/reusable/RecommendList";
import BestRecommend from "../UI/recommend/BestRecommend";

function Recommend() {
    const recommendResult = useStore((state) => state.recommendResult);
    let recommendBool = true;

    useEffect( () => {
        console.log(recommendResult);
        console.log(recommendResult.length);
        recommendResult.length > 0 ? recommendBool = true : recommendBool = false;
    }, [recommendResult])

    const conWrap = css `
      height: 100vh;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      align-items: center;
    `

    return (
        <div className="Recommend">
            <div css={conWrap}>
                {
                    recommendBool
                    ?
                    recommendResult.map((key, index) => {
                        return(
                            <>
                            key == 0
                            ?
                            <BestRecommend key={index} id={key.id} name={key.name}/>
                            :
                            <RecommendList key={index} id={key.id} name={key.name}/>
                            {/*<div key={index} css={testStyle}>*/}
                            {/*    {key.address}*/}
                            {/*</div>*/}
                            </>
                        )
                    })
                    :
                    <div>Loading</div>
                }
            </div>
        </div>
    );
}

export default Recommend;
