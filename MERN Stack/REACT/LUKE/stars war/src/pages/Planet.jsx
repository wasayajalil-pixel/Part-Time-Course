import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const Planet = () => {
  // Get the planet id from the URL
  const { id } = useParams();

  // Store the planet information
  const [planet, setPlanet] = useState(null);

  // Store the loading status
  const [loading, setLoading] = useState(true);

  // Store the error status
  const [error, setError] = useState(false);

  useEffect(() => {
    // Get one planet from the API
    const getPlanet = async () => {
      try {
        // Reset everything before making the request
        setLoading(true);
        setError(false);
        setPlanet(null);

        // Request the planet using its id
        const response = await axios.get(
          `https://www.swapi.tech/api/planets/${id}`,
        );

        // Check the API response in the console
        console.log("Planet response:", response.data);

        // Save only the planet properties
        setPlanet(response.data.result.properties);
      } catch (error) {
        // Print the real error in the console
        console.error("Planet request error:", error);

        // Show the error section
        setError(true);
      } finally {
        // Stop loading
        setLoading(false);
      }
    };

    getPlanet();
  }, [id]);

  // Show while waiting for the API
  if (loading) {
    return (
      <main>
        <h2>Loading planet...</h2>
      </main>
    );
  }

  // Show when the API request fails
  if (error) {
    return (
      <main>
        <h2>These aren't the droids you're looking for</h2>

        <img
          src="https://i.imgur.com/KVROw5X.jpeg"
          alt="Obi-Wan Kenobi"
          width="300"
        />
      </main>
    );
  }

  // Display the planet information
  return (
    <main>
      <h1>{planet.name}</h1>

      <p>
        <strong>Climate:</strong> {planet.climate}
      </p>

      <p>
        <strong>Terrain:</strong> {planet.terrain}
      </p>

      <p>
        <strong>Population:</strong> {planet.population}
      </p>

      <p>
        <strong>Gravity:</strong> {planet.gravity}
      </p>

      <p>
        <strong>Diameter:</strong> {planet.diameter} km
      </p>

      <p>
        <strong>Orbital Period:</strong> {planet.orbital_period} days
      </p>
    </main>
  );
};

export default Planet;