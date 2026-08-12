import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const Create = () => {
  const [name, setName] = useState("");
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  const submitHandler = async (e) => {
    e.preventDefault();

    try {
      await axios.post("http://localhost:8000/api/authors",{ name });

      navigate("/authors");

    } catch (error) {
      console.log(error);

      if (error.response?.data?.errors) {
        setErrors(error.response.data.errors);
      }
    }
  };

  return (
    <div>

      <h1>Favorite Authors</h1>

      <Link to="/authors">
        Home
      </Link>

      <h3>Add a new author:</h3>

      <form onSubmit={submitHandler}>

        <div>

          <label>Name:</label>

          <input
            type="text"
            value={name}
            onChange={(e) =>
              setName(e.target.value)
            }
          />

          {errors.name && (
            <p style={{ color: "red" }}>
              {errors.name.message}
            </p>
          )}

        </div>

        <Link to="/authors">
          Cancel
        </Link>

        <button type="submit">
          Submit
        </button>

      </form>

    </div>
  );
};

export default Create;