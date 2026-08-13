import { Route, Routes } from "react-router-dom";
import Register from "../pages/Register";
import Login from "../pages/Login";
import Dashboard from "../pages/Dashboard";
import Create from "../pages/Create";
import Details from "../pages/Details";
import Edit from "../pages/Edit";

function App() {
  return (
    <>
      <Routes>
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/products/new" element={<Create />} />
        <Route path="/products/:id" element={<Details />} />
        <Route path="/products/:id/edit" element={<Edit />} />
      </Routes>
    </>
  );
}

export default App;
