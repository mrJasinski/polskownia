import { BrowserRouter, Route, Routes } from "react-router-dom";
import MainLayout from "./app/layouts/MainLayout";
import Home from "./app/pages/home/Home";
import Articles from "./app/pages/article/Articles";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element = { <MainLayout /> }>
          <Route path="/" element={<Home />} />
          <Route path="/articles" element={<Articles />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;