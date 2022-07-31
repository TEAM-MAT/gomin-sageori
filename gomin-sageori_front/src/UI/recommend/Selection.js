/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';

function Selection(props) {
  const prevSelection = JSON.parse(localStorage.getItem('userSelection'));
  const preferSelection = JSON.parse(localStorage.getItem('preferSelection'));
  const preferArr = [
    '없음', 'none',
    '국물이 있는', 'soup',
    '매운', 'spicy',
    '뜨거운', 'hot',
    '달달한', 'sweet',
    '고기가 있는', 'meat',
    '면이 있는', 'noodle',
    '밥이 있는', 'rice',
    '빵이 있는', 'bread',
  ];

  function preferFilter(value) {
    const preferSelectionFiltered = [...preferSelection];
    value.map((elm, idx) => (
      preferSelectionFiltered.splice(idx, 1, preferArr[preferArr.indexOf(elm) - 1])
    ));
    return preferSelectionFiltered;
  }

  const selectionWrap = css`
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    width: 80vw;
    height: 40px;
    background-color: #FDEEE4;
    border-radius: 50px;
    margin: 20px auto;
  `;

  const selectionTitle = css`
    font-size: 1em;
    margin-right: 6px;
  `;

  const selectionCon = css`
    font-size: 0.85em;
    color: rgba(71, 71, 71, 0.87);
  `;

  const arrow = css`
    margin-left: 6px;
  `;

  return (
    <div css={selectionWrap}>
      <div css={selectionTitle}>선택 태그</div>
      <span css={selectionCon}>
        {
          preferFilter(preferSelection).map((elm, index) => (
            `${elm}, `
          ))
        }
        {
          `
            ${prevSelection.franchise ? '프랜차이즈 포함' : '프랜차이즈 미포함'}, 
            ${prevSelection.region}
        `
        }
      </span>
      <span css={arrow}>
        {'>'}
      </span>
    </div>
  );
}

export default Selection;
