import { Route, Routes } from "react-router-dom";
import Form from "./components/Form";
import People from "./pages/People";
import Planet from "./pages/Planet";


function App() {
  return (
    <>
      <Form />
      <Routes>
        <Route path="/people/:id" element={<People />} />
        <Route path="/planets/:id" element={<Planet />} />
      </Routes>
    </>
  );
}

export default App;
