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
  async setRecommendResult() {
    console.log("1");
    const axios = require("axios");
    await axios
      .get("/api/recommendation", {
        params: {
          characteristic: "spicy,noodle",
          franchise: false,
          location: "신촌",
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
