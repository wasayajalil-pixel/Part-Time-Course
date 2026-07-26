const pokémon = Object.freeze([
  { id: 1, name: "Bulbasaur", types: ["poison", "grass"] },
  { id: 5, name: "Charmeleon", types: ["fire"] },
  { id: 9, name: "Blastoise", types: ["water"] },
  { id: 12, name: "Butterfree", types: ["bug", "flying"] },
  { id: 16, name: "Pidgey", types: ["normal", "flying"] },
  { id: 23, name: "Ekans", types: ["poison"] },
  { id: 24, name: "Arbok", types: ["poison"] },
  { id: 25, name: "Pikachu", types: ["electric"] },
  { id: 37, name: "Vulpix", types: ["fire"] },
  { id: 52, name: "Meowth", types: ["normal"] },
  { id: 63, name: "Abra", types: ["psychic"] },
  { id: 67, name: "Machamp", types: ["fighting"] },
  { id: 72, name: "Tentacool", types: ["water", "poison"] },
  { id: 74, name: "Geodude", types: ["rock", "ground"] },
  { id: 87, name: "Dewgong", types: ["water", "ice"] },
  { id: 98, name: "Krabby", types: ["water"] },
  { id: 115, name: "Kangaskhan", types: ["normal"] },
  { id: 122, name: "Mr. Mime", types: ["psychic"] },
  { id: 133, name: "Eevee", types: ["normal"] },
  { id: 144, name: "Articuno", types: ["ice", "flying"] },
  { id: 145, name: "Zapdos", types: ["electric", "flying"] },
  { id: 146, name: "Moltres", types: ["fire", "flying"] },
  { id: 148, name: "Dragonair", types: ["dragon"] },
]);

// Pokémon objects whose id is evenly divisible by 3
const divisibleByThree = pokémon.filter((pokemon) => pokemon.id % 3 === 0);

console.log(divisibleByThree);

// Pokémon objects that are fire type
const firePokemon = pokémon.filter((pokemon) =>
  pokemon.types.includes("fire")
);

console.log(firePokemon);

// Pokémon objects that have more than one type
const multipleTypePokemon = pokémon.filter((pokemon) => pokemon.types.length > 1);

console.log(multipleTypePokemon);

// Array containing only Pokémon names
const pokemonNames = pokémon.map((pokemon) => pokemon.name);

console.log(pokemonNames);

// Names of Pokémon with an id greater than 99
const namesWithIdGreaterThan99 = pokémon.filter((pokemon) => pokemon.id > 99).map((pokemon) => pokemon.name);

console.log(namesWithIdGreaterThan99);

// Names of Pokémon whose only type is poison
const onlyPoisonPokemonNames = pokémon.filter((pokemon) =>pokemon.types.length === 1 && pokemon.types[0] === "poison").map((pokemon) => pokemon.name);

console.log(onlyPoisonPokemonNames);

// First type of Pokémon whose second type is flying
const firstTypesWithFlyingSecond = pokémon.filter((pokemon) => pokemon.types[1] === "flying").map((pokemon) => pokemon.types[0]);

console.log(firstTypesWithFlyingSecond);

// Count of Pokémon that are normal type
const normalPokemonCount = pokémon.filter((pokemon) => pokemon.types.includes("normal")).length;

console.log(normalPokemonCount);