import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const Dashboard = () => {
  const [authors, setAuthors] = useState([]);

  useEffect(() => {
    const getAuthors = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8000/api/authors"
        );

        setAuthors(response.data.authors);
      } catch (error) {
        console.log(error);
      }
    };

    getAuthors();
  }, []);

  const deleteAuthor = async (id) => {
    try {
      await axios.delete(
        `http://localhost:8000/api/authors/${id}`
      );

      // Remove author from DOM without refreshing
      setAuthors(
        authors.filter((author) => author._id !== id)
      );
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>

      <h1>Favorite Authors</h1>

      <Link to="/authors/new">
        Add an author
      </Link>

      <p>We have quotes by:</p>

      <table>

        <thead>
          <tr>
            <th>Author</th>
            <th>Actions available</th>
          </tr>
        </thead>

        <tbody>

          {authors.map((author) => (
            <tr key={author._id}>

              <td>
                {author.name}
              </td>

              <td>

                <Link to={`/authors/${author._id}/edit`}>
                  Edit
                </Link>

                {" "}

                <button onClick={() => deleteAuthor(author._id)}>
                  Delete
                </button>

              </td>

            </tr>
          ))}

        </tbody>

      </table>

    </div>
  );
};

export default Dashboard;