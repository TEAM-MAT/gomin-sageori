/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import { useEffect } from 'react';
import useStore from '../state/store';
import RecommendList from '../UI/reusable/RecommendList';
import BestRecommend from '../UI/recommend/BestRecommend';
import Selection from '../UI/recommend/Selection';

const shortId = require('shortid');

function Recommend() {
  const recommendResult = useStore(state => state.recommendResult);
  let recommendBool = true;

  useEffect(() => {
    if (recommendResult.length > 0) recommendBool = true;
    else recommendBool = false;
  }, [recommendResult]);

  const conWrap = css`
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
  `;

  return (
    <div className="Recommend">
      <div>
        <Selection />
      </div>
      <div css={conWrap}>
        {/* <BestRecommend id={recommendResult[0].id} name={recommendResult[0].name}/> */}
        {recommendBool ? (
          recommendResult.map((key, index) => {
            if (index !== 0) {
              return (
                <>
                  <RecommendList key={shortId} id={key.id} name={key.name} />
                  {/* <div key={index} css={testStyle}> */}
                  {/*    {key.address} */}
                  {/* </div> */}
                </>
              );
            }
            return (
              <>
                <BestRecommend key={shortId} id={key.id} name={key.name} />
                {/* <div key={index} css={testStyle}> */}
                {/*    {key.address} */}
                {/* </div> */}
              </>
            );
          })
        ) : (
          <div>Loading</div>
        )}
      </div>
    </div>
  );
}

export default Recommend;
