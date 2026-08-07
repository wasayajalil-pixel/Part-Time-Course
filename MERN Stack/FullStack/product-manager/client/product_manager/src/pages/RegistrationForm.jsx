import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const RegistrationForm = () => {
  const [title, setTitle] = useState("");
  const [price, setPrice] = useState(0);
  const [description, setDescription] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      await axios.post("http://localhost:8000/api/products", {
        title,
        price,
        description,
      });

      navigate("/dashboard");
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="title">Title :</label>
        <input
          type="text"
          id="title"
          onChange={(event) => setTitle(event.target.value)}
        />
      </div>

      <div>
        <label htmlFor="price">Price :</label>
        <input
          type="number"
          id="price"
          onChange={(event) => setPrice(event.target.value)}
        />
      </div>

      <div>
        <label htmlFor="description">Description :</label>
        <input
          type="text"
          id="description"
          onChange={(event) => setDescription(event.target.value)}
        />
      </div>

      <button type="submit" value="Register">Create</button>
    </form>
  );
};

export default RegistrationForm;