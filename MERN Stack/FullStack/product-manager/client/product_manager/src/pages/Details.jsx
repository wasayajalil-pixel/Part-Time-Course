import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

const Details = () => {
  const [product, setProduct] = useState();

  const { id } = useParams();

  useEffect(() => {
    const getProduct = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8000/api/products/${id}`
        );

        setProduct(response.data.product);
      } catch (error) {
        console.log(error);
      }
    };

    getProduct();
  }, [id]);

  if (!product) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      <h1>{product.title}</h1>

      <p>Price: ${product.price}</p>

      <p>Description: {product.description}</p>
      
       <Link to="/dashboard">← Back to Dashboard</Link>
      
    </div>
  );
};

export default Details;