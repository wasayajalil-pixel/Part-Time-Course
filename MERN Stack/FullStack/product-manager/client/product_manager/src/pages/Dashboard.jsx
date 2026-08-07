import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const Dashboard = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    const getProducts = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8000/api/products"
        );

        setProducts(response.data.products);
      } catch (error) {
        console.log(error);
      }
    };

    getProducts();
  }, []);

  const deleteProduct = async (id) => {
    try {
      await axios.delete(
        `http://localhost:8000/api/products/${id}`
      );

      setProducts(
        products.filter((product) => product._id !== id)
      );
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div>
      <h1>All Products</h1>

      {products.map((product) => (
        <div key={product._id}>
          <h2>
            <Link to={`/products/${product._id}`}>
              {product.title}
            </Link>
          </h2>

          <p>Price: ${product.price}</p>

          <p>
            Description: {product.description}
          </p>

          <Link to={`/products/${product._id}/edit`}>
            Edit
          </Link>

          {" "}

          <button
            onClick={() => deleteProduct(product._id)}
          >
            Delete
          </button>

          <hr />
        </div>
      ))}
    </div>
  );
};

export default Dashboard;