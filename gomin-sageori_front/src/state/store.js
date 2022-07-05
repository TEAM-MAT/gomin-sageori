import create from "zustand";

const useStore = create((set) => ({
    // isAllSelect: false,
    // setIsAllSelect: (bool) => set((state) => ({ isAllSelect: bool })),
    recommendResult: [],
    async setRecommendResult() {
        console.log('1')
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
                set(() => ({recommendResult: response.data.restaurants}))
                // console.log(response.data.restaurants)
            });
    }
}));

export default useStore;