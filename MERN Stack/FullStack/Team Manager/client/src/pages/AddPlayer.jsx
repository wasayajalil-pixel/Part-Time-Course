import { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

const AddPlayer = () => {
  // Used for redirect
  const navigate = useNavigate();

  // Form states
  const [name, setName] = useState("");
  const [position, setPosition] = useState("");

  // Backend errors
  const [errors, setErrors] = useState({});

  // ==========================
  // ADD PLAYER
  // ==========================
  const handleSubmit = async (e) => {
    // Stop page refresh
    e.preventDefault();

    try {
      await axios.post("http://localhost:8000/api/players", name, position);

      // If successful:
      // go back to player list
      navigate("/players/list");
    } catch (error) {
      console.log(error);

      // Get backend validations
      if (error.response?.data?.errors) {
        setErrors(error.response.data.errors);
      }
    }
  };

  return (
    <div>
      <h1>Team Manager</h1>

      {/* Navigation */}
      <div>
        <Link to="/players/list">Player List</Link>

        {" | "}

        <Link to="/players/add">Add Player</Link>

        {" | "}

        <Link to="/status/game/1">Player Status</Link>
      </div>

      <hr />

      <h2>Add Player</h2>

      <form onSubmit={handleSubmit}>
        {/* PLAYER NAME */}
        <div>
          <label>Player Name:</label>

          <br />

          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />

          {/* Backend validation */}
          {errors.name && <p style={{ color: "red" }}>{errors.name.message}</p>}
        </div>

        <br />

        {/* POSITION */}
        <div>
          <label>Preferred Position:</label>

          <br />

          <input
            type="text"
            value={position}
            onChange={(e) => setPosition(e.target.value)}
          />
        </div>

        <br />

        <button type="submit">Add Player</button>
      </form>
    </div>
  );
};

export default AddPlayer;
