import { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

// Material UI
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";
import Alert from "@mui/material/Alert";

const Create = () => {
  // Form states
  const [title, setTitle] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");

  // Backend validation errors
  const [errors, setErrors] = useState({});

  const navigate = useNavigate();

  // =========================
  // CREATE PRODUCT
  // =========================
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await axios.post(
        "http://localhost:8000/api/products",
        {
          title,
          price,
          description,
        },
        {
          withCredentials: true,
        }
      );

      // Clear errors
      setErrors({});

      // Go back to dashboard
      navigate("/dashboard");

    } catch (error) {
      console.log(error.response?.data);

      // Example backend:
      // res.status(400).json({ errors: error.errors })

      setErrors(error.response?.data?.errors || {});
    }
  };

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
          Create Product
        </Typography>

        <Typography
          color="text.secondary"
          sx={{ mb: 3 }}
        >
          Add a new product to your dashboard.
        </Typography>

        {/* General backend error */}
        {errors.message && (
          <Alert
            severity="error"
            sx={{ mb: 2 }}
          >
            {errors.message}
          </Alert>
        )}

        {/* Form */}
        <Box
          component="form"
          onSubmit={handleSubmit}
        >
          <Stack spacing={2.5}>

            {/* Product Title */}
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
                Create Product
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

export default Create;