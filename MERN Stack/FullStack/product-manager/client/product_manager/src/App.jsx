
import { Routes, Route } from "react-router-dom";
import RegistrationForm from "./pages/RegistrationForm";
import Dashboard from "./pages/Dashboard";
import Details from "./pages/Details";
import Edit from "./pages/Edit";

function App() {
  return (
    <Routes>
      <Route path="/register" element={<RegistrationForm />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/products/:id" element={<Details />} />
      <Route path="/products/:id/edit" element={<Edit />} />
    </Routes>
  );
}

export default App;