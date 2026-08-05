import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Form = () => {
  // Store the selected resource from people.
  const [resource, setResource] = useState("people");

  // Store the ID entered by the user
  const [id, setId] = useState("");

  // Allows us to navigate to another route
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    // Prevent the browser from refreshing
    event.preventDefault();

    // Stop if the ID is empty or invalid
    if (id === "" || Number(id) < 1) {
      alert("Please enter ID and must be more than 0")
      return;
    }

    // Navigate to /people/1 or /planets/1
    navigate(`/${resource}/${id}`);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="resource">Search for:</label>

      <select
        value={resource}
        onChange={(event) => setResource(event.target.value)}
      >
        <option value="people">Characters</option>
        <option value="planets">Planets</option>
      </select>

      <label htmlFor="id">ID:</label>

      <input
        type="number"
        value={id}
        onChange={(event) => setId(event.target.value)}
      />

      <button type="submit">Search</button>
    </form>
  );
};

export default Form;