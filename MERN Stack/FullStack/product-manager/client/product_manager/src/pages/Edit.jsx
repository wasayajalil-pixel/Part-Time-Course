import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const Edit = () => {
  const { id } = useParams();

  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");

  useEffect(() => {
    const getProduct = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8000/api/products/${id}`
        );

        setTitle(response.data.product.title);
        setPrice(response.data.product.price);
        setDescription(response.data.product.description);
      } catch (error) {
        console.log(error);
      }
    };

    getProduct();
  }, [id]);

  const updateProduct = async (e) => {
    e.preventDefault();

    try {
      await axios.put(
        `http://localhost:8000/api/products/${id}`,
        {
          title,
          price,
          description,
        }
      );

      navigate(`/products/${id}`);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>
      <h1>Edit Product</h1>

      <form onSubmit={updateProduct}>
        <div>
          <label>Title:</label>

          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>

        <div>
          <label>Price:</label>

          <input
            type="number"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
          />
        </div>

        <div>
          <label>Description:</label>

          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </div>

        <button type="submit">
          Update Product
        </button>
      </form>
    </div>
  );
};

export default Edit;