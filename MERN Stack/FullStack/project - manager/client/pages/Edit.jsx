import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate, useParams, Link } from "react-router-dom";

// Material UI
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";
import CircularProgress from "@mui/material/CircularProgress";

const Edit = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  // Form states
  const [title, setTitle] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");

  // Validation errors
  const [errors, setErrors] = useState({});

  // Loading state
  const [loading, setLoading] = useState(true);

  // =========================
  // GET PRODUCT BY ID
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

        // If backend returns { product }
        const product = response.data.product;

        // Auto-fill the form
        setTitle(product.title);
        setPrice(product.price);
        setDescription(product.description);

      } catch (error) {
        console.log(error);
      } finally {
        setLoading(false);
      }
    };

    getProduct();
  }, [id]);

  // =========================
  // UPDATE PRODUCT
  // =========================
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await axios.put(
        `http://localhost:8000/api/products/${id}`,
        {
          title,
          price,
          description,
        },
        {
          withCredentials: true,
        }
      );

      // Remove old errors
      setErrors({});

      // Go back to dashboard
      navigate("/dashboard");

    } catch (error) {
      console.log(error.response?.data);

      setErrors(
        error.response?.data?.errors || {}
      );
    }
  };

  // Show spinner while product is loading
  if (loading) {
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
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        p: 2,
      }}
    >
      <Paper
        elevation={5}
        sx={{
          width: "100%",
          maxWidth: 550,
          p: 4,
          borderRadius: 3,
        }}
      >
        {/* Header */}
        <Typography
          variant="h4"
          fontWeight="bold"
          gutterBottom
        >
          Edit Product
        </Typography>

        <Typography
          color="text.secondary"
          sx={{ mb: 3 }}
        >
          Update your product information.
        </Typography>

        {/* Form */}
        <Box
          component="form"
          onSubmit={handleSubmit}
        >
          <Stack spacing={2.5}>

            {/* Title */}
            <TextField
              label="Product Title"
              value={title}
              onChange={(e) =>
                setTitle(e.target.value)
              }
              fullWidth
              error={!!errors.title}
              helperText={
                errors.title?.message
              }
            />

            {/* Price */}
            <TextField
              label="Price"
              type="number"
              value={price}
              onChange={(e) =>
                setPrice(e.target.value)
              }
              fullWidth
              error={!!errors.price}
              helperText={
                errors.price?.message
              }
            />

            {/* Description */}
            <TextField
              label="Description"
              value={description}
              onChange={(e) =>
                setDescription(e.target.value)
              }
              multiline
              rows={4}
              fullWidth
              error={!!errors.description}
              helperText={
                errors.description?.message
              }
            />

            {/* Buttons */}
            <Stack
              direction="row"
              spacing={2}
            >
              <Button
                type="submit"
                variant="contained"
                fullWidth
              >
                Update Product
              </Button>

              <Button
                component={Link}
                to="/dashboard"
                variant="outlined"
                fullWidth
              >
                Cancel
              </Button>
            </Stack>

          </Stack>
        </Box>
      </Paper>
    </Box>
  );
};

export default Edit;