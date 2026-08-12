import axios from "axios";
import { useEffect, useState } from "react";

import {
  Link,
  useNavigate,
  useParams,
} from "react-router-dom";

const Edit = () => {

  const { id } = useParams();

  const navigate = useNavigate();

  const [name, setName] = useState("");

  const [errors, setErrors] = useState({});

  const [notFound, setNotFound] =
    useState(false);

  useEffect(() => {

    const getAuthor = async () => {

      try {

        const response = await axios.get(
          `http://localhost:8000/api/authors/${id}`
        );

        setName(
          response.data.author.name
        );

      } catch (error) {

        console.log(error);

        setNotFound(true);

      }

    };

    getAuthor();

  }, [id]);



  const submitHandler = async (e) => {

    e.preventDefault();

    try {

      await axios.put(
        `http://localhost:8000/api/authors/${id}`,
        {
          name,
        }
      );

      navigate("/authors");

    } catch (error) {

      console.log(error);

      if (error.response?.data?.errors) {

        setErrors(
          error.response.data.errors
        );

      }

    }

  };



  if (notFound) {

    return (
      <div>

        <h2>Favorite Authors</h2>

        <p>
          We apologize, but we couldn't
          locate the author you're searching for.
        </p>

        <p>
          Would you like to add this author
          to our database?
        </p>

        <Link to="/authors/new">
          Add an Author
        </Link>

      </div>
    );
  }



  return (
    <div>

      <h1>Favorite Authors</h1>

      <Link to="/authors">
        Home
      </Link>

      <h3>Edit this author:</h3>

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

export default Edit;