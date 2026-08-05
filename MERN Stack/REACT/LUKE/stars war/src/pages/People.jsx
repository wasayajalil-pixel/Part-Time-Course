import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const People = () => {
  // Get the ID from the URL
  const { id } = useParams();
  // Stores the character returned from the API.
  const [person, setPerson] = useState(null);
  // Stores whether an error happened.
  const [error, setError] = useState(false);
  // Stores the loading state.
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getPerson = async () => {
      try {
        // Start loading and remove the previous error.
        setLoading(true);
        setError(false);
        setPerson(null);

        // Request one character using the ID from the URL.
        const response = await axios.get(`https://swapi.py4e.com/api/people/${id}/`);

        // Save the character data in state.
        setPerson(response.data);
      } catch (error) {
        console.error(error);

        // Display the Obi-Wan error section.
        setError(true);
      } finally {
        // Stop loading whether the request succeeds or fails.
        setLoading(false);
      }
    };

    getPerson();

    // Run the effect again whenever the ID changes.
  }, [id]);

  if (loading) {
    return (
      <main>
        <h2>Loading character...</h2>
      </main>
    );
  }

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

  return (
    <main>
      <h1>{person.name}</h1>

      {/* Display at least four character attributes. */}
      <p>
        <strong>Height:</strong> {person.height} cm
      </p>

      <p>
        <strong>Mass:</strong> {person.mass} kg
      </p>

      <p>
        <strong>Hair Color:</strong> {person.hair_color}
      </p>

      <p>
        <strong>Skin Color:</strong> {person.skin_color}
      </p>
    </main>
  );
};

export default People;
