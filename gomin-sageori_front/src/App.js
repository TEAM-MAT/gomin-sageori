import { Route, Routes } from "react-router-dom";
import Main from "./pages/Main";
import Recommend from "./pages/Recommend";

function App() {
  return (
    <div className="App">
    <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/recommend" element={<Recommend />} />
    </Routes>
    </div>
  );
}

export default App;
