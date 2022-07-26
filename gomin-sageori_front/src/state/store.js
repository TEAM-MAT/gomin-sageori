import create from 'zustand';

const axios = require('axios');

const useStore = create((set) => ({
  // isAllSelect: false,
  // setIsAllSelect: (bool) => set((state) => ({ isAllSelect: bool })),
  userSelection: {
    prefer: new Set(),
    atmosphere: '',
    allergy: '',
    franchise: false,
    region: '',
  },
  setUserSelection: p => set(() => ({ userSelection: p })),
  recommendResult: [],
  async setRecommendResult(selection) {
    const preferStr = Array.from(selection.prefer).join(',');
    await axios
      .get('/api/recommendation', {
        params: {
          characteristic: preferStr,
          franchise: selection.franchise,
          location: selection.region,
        },
      })
      .then(response => {
        set(() => ({ recommendResult: response.data.restaurants }));
      });
  },
  isPreferMaxSelect: { isMax: false, maxNumber: 3 },
  toggleIsPreferMaxSelect: p =>
    set(() => ({
      isPreferMaxSelect: { isMax: p },
    })),
}));

export default useStore;
