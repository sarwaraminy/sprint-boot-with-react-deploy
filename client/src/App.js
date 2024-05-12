
import NavLink from "./component/NavLink";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import RoomData from "./component/Room";
import './App.css'

function App() {
  return (
    
    <BrowserRouter>
      <NavLink />
      <Routes>
        <Route path="/rooms/api" element={<RoomData />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
