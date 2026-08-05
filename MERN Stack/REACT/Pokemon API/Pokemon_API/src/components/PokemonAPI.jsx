import { useState } from "react";

const PokemonAPI = () => {
//because no Pokémon have been fetched yet i use empty array.
  const [pokemon, setPokemon] = useState([]);
//gets pokemon from the api ,async take time so i handle it by try and catch.
  const fetchPokemon = async () => {
    try {
      //send a GET request to the Pokemon API
      const response = await fetch(
        "https://pokeapi.co/api/v2/pokemon?limit=807"
      );
      //convert the response into a JS object.
      const data = await response.json();

      setPokemon(data.results);

    } catch (error) {
      console.log(error);
    }
  };

  //map is loop through the array and display each Pokemon's name.
  return (
    <div>

      <button onClick={fetchPokemon}>
        Fetch Pokemon
      </button>

      {pokemon.map((poke, index) => (
        <p key={index}>{poke.name}</p>
      ))}

    </div>
  );
};

export default PokemonAPI;