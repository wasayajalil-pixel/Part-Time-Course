import { useEffect, useState } from "react";
import axios from "axios";
import {
  useNavigate,
  useParams,
  Link,
} from "react-router-dom";

// Material UI
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";
import Divider from "@mui/material/Divider";
import CircularProgress from "@mui/material/CircularProgress";

const Details = () => {
  // Product from backend
  const [product, setProduct] = useState(null);

  const { id } = useParams();

  const navigate = useNavigate();

  // =========================
  // GET ONE PRODUCT
  // =========================
  useEffect(() => {
    const getProduct = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8000/api/products/${id}`,
          {
            withCredentials: true,
          }
        );

        console.log(response.data);

        /*
          If backend sends:

          res.json({
            product
          })

          use:
          response.data.product
        */

        setProduct(response.data.product);

      } catch (error) {
        console.log(error);
      }
    };

    getProduct();

  }, [id]);

  // =========================
  // DELETE PRODUCT
  // =========================
  const handleDelete = async () => {
    try {
      await axios.delete(
        `http://localhost:8000/api/products/${id}`,
        {
          withCredentials: true,
        }
      );

      // After deleting go back
      navigate("/dashboard");

    } catch (error) {
      console.log(error);
    }
  };

  // Loading
  if (!product) {
    return (
      <Box
        sx={{
          minHeight: "100vh",
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <CircularProgress />
      </Box>
    );
  }

  return (
    <Box
      sx={{
        minHeight: "100vh",
        backgroundColor: "#f5f5f5",
        p: 4,
      }}
    >
      <Box
        sx={{
          maxWidth: 800,
          mx: "auto",
        }}
      >

        {/* Back Button */}
        <Button
          component={Link}
          to="/dashboard"
          variant="text"
          sx={{ mb: 2 }}
        >
          ← Back to Dashboard
        </Button>

        {/* Product Card */}
        <Paper
          elevation={4}
          sx={{
            p: 4,
            borderRadius: 3,
          }}
        >
          <Typography
            variant="h4"
            fontWeight="bold"
          >
            {product.title}
          </Typography>

          <Typography
            variant="h6"
            color="primary"
            sx={{ mt: 1 }}
          >
            ${product.price}
          </Typography>

          <Divider
            sx={{
              my: 3,
            }}
          />

          <Typography
            variant="subtitle1"
            fontWeight="bold"
          >
            Description
          </Typography>

          <Typography
            color="text.secondary"
            sx={{
              mt: 1,
              mb: 4,
            }}
          >
            {product.description}
          </Typography>

          {/* Actions */}
          <Stack
            direction="row"
            spacing={2}
          >
            {/* Edit */}
            <Button
              component={Link}
              to={`/products/${id}/edit`}
              variant="contained"
            >
              Edit Product
            </Button>

            {/* Delete */}
            <Button
              color="error"
              variant="outlined"
              onClick={handleDelete}
            >
              Delete Product
            </Button>

          </Stack>
        </Paper>

      </Box>
    </Box>
  );
};

export default Details;