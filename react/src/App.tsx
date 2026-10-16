import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./app/pages/home/Home";
import Articles from "./app/pages/article/Articles";
import Header from "./app/components/header/Header";
import Contact from "./app/pages/contact/Contact";
import Shop from "./app/pages/shop/Shop";

function App() {
  return (
    <BrowserRouter>

      <Header/>

      <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/shop" element={<Shop />} />
          <Route path="/articles" element={<Articles />} />
          <Route path="/contact" element={<Contact />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;