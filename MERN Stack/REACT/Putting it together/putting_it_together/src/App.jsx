import { useState } from "react";
import "./App.css";
import Card from "./components/Card";

function App() {
  return (
    <div>
      <Card 
      firstName={"Jane"} 
      lastName={"Doe"} 
      age={45} 
      hairColor={"Black"}
      />

      <Card
        firstName={"John"}
        lastName={"Smith"}
        age={88}
        hairColor={"Brown"}
      />
    </div>
  );
}

export default App;
