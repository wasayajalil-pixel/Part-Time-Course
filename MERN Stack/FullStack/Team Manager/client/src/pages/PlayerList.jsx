import { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const PlayerList = () => {

  // Store all players
  const [players, setPlayers] = useState([]);

  // ==========================
  // GET ALL PLAYERS
  // ==========================
  useEffect(() => {

    const getPlayers = async () => {
      try {

        const response = await axios.get(
          "http://localhost:8000/api/players"
        );

        setPlayers(response.data.players);

      } catch (error) {
        console.log(error);
      }
    };

    getPlayers();

  }, []);

  // ==========================
  // DELETE PLAYER
  // ==========================
  const deletePlayer = async (id) => {

    // Bonus confirmation popup
    const answer = window.confirm(
      "Are you sure you want to delete this player?"
    );

    if (!answer) {
      return;
    }

    try {

      await axios.delete(
        `http://localhost:8000/api/players/${id}`
      );

      // Remove deleted player from screen
      // without refreshing
      setPlayers(
        players.filter(
          (player) => player._id !== id
        )
      );

    } catch (error) {
      console.log(error);
    }
  };


  return (
    <div>

      <h1>Team Manager</h1>

      {/* Navigation */}
      <div>

        <Link to="/players/list">
          Player List
        </Link>

        {" | "}

        <Link to="/players/add">
          Add Player
        </Link>

        {" | "}

        <Link to="/status/game/1">
          Player Status
        </Link>

      </div>

      <hr />

      <h2>Player List</h2>


      {/* Player Table */}
      <table border="1">

        <thead>

          <tr>
            <th>Player Name</th>
            <th>Preferred Position</th>
            <th>Actions</th>
          </tr>

        </thead>

        <tbody>

          {players.map((player) => (

            <tr key={player._id}>

              <td>
                {player.name}
              </td>

              <td>
                {player.position}
              </td>

              <td>

                <button
                  onClick={() =>
                    deletePlayer(player._id)
                  }
                >
                  Delete
                </button>

              </td>

            </tr>

          ))}

        </tbody>

      </table>

    </div>
  );
};

export default PlayerList;