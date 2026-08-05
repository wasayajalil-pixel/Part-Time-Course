import axios from "axios";
import { useState } from "react";

const PokemonAPI = () => {
  const [pokemon, setPokemon] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const fetchPokemon = async () => {
    try {
      // Show loading message and if there any error will show the error message
      setLoading(true);
      setError("");
      //get request using Axios
      const response = await axios.get(
        "https://pokeapi.co/api/v2/pokemon?limit=807",
      );
      // Save the Pokemon array into state
      setPokemon(response.data.results);
    } catch (error) {
      console.log(error);
      setError("Something went wrong while loading the Pokémon.");
    // Runs whether the request succeeds or fails
    } finally {
      setLoading(false);
    }
  };
  return (
    <div className="app">
      <h1>Pokémon API with Axios</h1>

      <button onClick={fetchPokemon} disabled={loading}>
        {loading ? "Loading..." : " Fetch Pokémon"}
      </button>

      {error && <p className="error">{error}</p>}

      <ul>
        {pokemon.map((item, index) => (
          <li key={item.name}>
            {index + 1}. {item.name}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PokemonAPI;
