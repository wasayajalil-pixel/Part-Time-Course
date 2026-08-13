import { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

const PlayerStatus = () => {

  // Get 1, 2 or 3 from URL
  const { gameNumber } = useParams();
  // Store players
  const [players, setPlayers] = useState([]);
  // GET ALL PLAYERS
  useEffect(() => {
    const getPlayers = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8000/api/players"
        );
        setPlayers(response.data.players);

      } catch (error) { console.log(error); }
    };
    getPlayers();
  }, []);
  // UPDATE PLAYER STATUS
  const updateStatus = async (playerId,status) => {
    try {
      // Example:
      // game 1 → game1Status
      // game 2 → game2Status
      // game 3 → game3Status

      const statusField =`game${gameNumber}Status`;
      const response = await axios.put(
        `http://localhost:8000/api/players/${playerId}`,
        {
          [statusField]: status
        }
      );
      // Updated player returned
      // from backend
      const updatedPlayer =
        response.data.player;


      // Replace old player
      // with updated player
      setPlayers(

        players.map((player) =>

          player._id === playerId
            ? updatedPlayer
            : player

        )

      );


    } catch (error) {
      console.log(error);
    }
  };


  return (
    <div>

      <h1>Team Manager</h1>
      {/* Main Navigation */}
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


      <h2>
        Player Status - Game {gameNumber}
      </h2>


      {/* Game navigation */}
      <div>

        <Link to="/status/game/1">
          Game 1
        </Link>

        {" | "}

        <Link to="/status/game/2">
          Game 2
        </Link>

        {" | "}

        <Link to="/status/game/3">
          Game 3
        </Link>

      </div>


      <br />


      {/* Players */}
      {players.map((player) => {

        // Example:
        //
        // player["game1Status"]
        //
        // Playing / Not Playing / Undecided

        const statusField =
          `game${gameNumber}Status`;

        const currentStatus =
          player[statusField];


        return (

          <div key={player._id}>

            <h3>
              {player.name}
            </h3>


            {/* PLAYING */}
            <button
              onClick={() =>
                updateStatus(
                  player._id,
                  "Playing"
                )
              }
              style={{
                backgroundColor:
                  currentStatus === "Playing"
                    ? "green"
                    : "white"
              }}
            >

              Playing

            </button>


            {/* NOT PLAYING */}
            <button
              onClick={() =>
                updateStatus(
                  player._id,
                  "Not Playing"
                )
              }
              style={{
                backgroundColor:
                  currentStatus ===
                  "Not Playing"
                    ? "red"
                    : "white"
              }}
            >

              Not Playing

            </button>


            {/* UNDECIDED */}
            <button
              onClick={() =>
                updateStatus(
                  player._id,
                  "Undecided"
                )
              }
              style={{
                backgroundColor:
                  currentStatus ===
                  "Undecided"
                    ? "yellow"
                    : "white"
              }}
            >

              Undecided

            </button>


            <hr />

          </div>

        );

      })}

    </div>
  );
};

export default PlayerStatus;