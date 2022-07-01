/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import useStore from "../state/store";
import {useEffect, useState} from "react";
import RecommendList from "../UI/reusable/RecommendList";

function Recommend() {
    const recommendResult = useStore((state) => state.recommendResult);

    useEffect( () => {
        console.log(recommendResult);
    }, [recommendResult])

    return (
        <div className="Recommend">
            {/*<input value={query} onChange={(event)=> {*/}
            {/*    setQuery(event.target.value)*/}
            {/*}}/>*/}
            {/*{*/}
            {/*    recommendResult.length == 0*/}
            {/*    ?*/}
            {/*    <div>Loading</div>*/}
            {/*    :*/}
            {/*    recommendResult.map((key, index) => {*/}
            {/*        // <RecommendList id={recommendResult[index].id}/>*/}
            {/*        <div key={index}>{key.address}</div>*/}
            {/*    })*/}
            {/*}*/}
        </div>
    );
}

export default Recommend;
