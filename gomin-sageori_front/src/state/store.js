import create from "zustand";

const useStore = create((set) => ({
  // isAllSelect: false,
  // setIsAllSelect: (bool) => set((state) => ({ isAllSelect: bool })),
  userSelection: {
    prefer: new Set(),
    atmosphere: "",
    allergy: "",
    franchise: false,
    region: "",
  },
  setUserSelection: (p) => set(() => ({ userSelection: p })),
  recommendResult: [],
  async setRecommendResult(sel) {
    console.log("1");
    const preferStr = Array.from(sel.prefer).join(",");
    console.log(sel.region);
    const axios = require("axios");
    await axios
      .get("/api/recommendation", {
        params: {
          characteristic: preferStr,
          franchise: sel.franchise,
          location: sel.region,
        },
      })
      .then((response) => {
        set(() => ({ recommendResult: response.data.restaurants }));
        // console.log(response.data.restaurants);
      });
  },
  isPreferMaxSelect: { isMax: false, maxNumber: 3 },
  toggleIsPreferMaxSelect: (p) =>
    set(() => ({
      isPreferMaxSelect: { isMax: p },
    })),
}));

export default useStore;
