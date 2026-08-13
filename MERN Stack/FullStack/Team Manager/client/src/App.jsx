import { Routes, Route, Navigate } from "react-router-dom";

import PlayerList from "./pages/PlayerList";
import AddPlayer from "./pages/AddPlayer";
import PlayerStatus from "./pages/PlayerStatus";

function App() {
  return (
      <Routes>

        {/* Default page */}
        <Route
          path="/"
          element={<Navigate to="/players/list" />}
        />

        {/* Show all players */}
        <Route
          path="/players/list"
          element={<PlayerList />}
        />

        {/* Add new player */}
        <Route
          path="/players/add"
          element={<AddPlayer />}
        />

        {/* Player Status */}
        <Route
          path="/status/game/:gameNumber"
          element={<PlayerStatus />}
        />

      </Routes>

  );
}

export default App;