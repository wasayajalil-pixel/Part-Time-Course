import { BrowserRouter, Routes, Route } from "react-router-dom";

import Dashboard from "./pages/Dashboard";
import Create from "./pages/Create";
import Edit from "./pages/Edit";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route
          path="/authors"
          element={<Dashboard />}
        />

        <Route
          path="/authors/new"
          element={<Create />}
        />

        <Route
          path="/authors/:id/edit"
          element={<Edit />}
        />

      </Routes>
    </BrowserRouter>
  );
}

export default App;