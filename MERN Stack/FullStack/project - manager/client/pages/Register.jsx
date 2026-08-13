import { useState } from "react";
import UserForm from "../components/UserForm";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

// Material UI
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Typography from "@mui/material/Typography";

const Register = () => {
  // Store backend validation errors
  const [errors, setErrors] = useState({});

  const navigate = useNavigate();

  const handleSubmit = async (e, data) => {
    e.preventDefault();

    try {
      await axios.post(
        "http://localhost:8000/api/register",
        data,
        {
          withCredentials: true,
        }
      );

      setErrors({});

      navigate("/dashboard");
    } catch (error) {
      console.log(error.response?.data);

      setErrors(error.response?.data?.errors || {});
    }
  };

  return (
    // Main page container
    <Box
      sx={{
        minHeight: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        backgroundColor: "#f5f5f5",
        p: 2,
      }}
    >
      {/* Registration Card */}
      <Paper
        elevation={6}
        sx={{
          width: "100%",
          maxWidth: 500,
          p: 4,
          borderRadius: 3,
        }}
      >
        {/* Title */}
        <Typography
          variant="h4"
          align="center"
          fontWeight="bold"
          gutterBottom
        >
          Create Account
        </Typography>

        {/* Small description */}
        <Typography
          variant="body2"
          align="center"
          color="text.secondary"
          sx={{ mb: 3 }}
        >
          Register to start managing your products
        </Typography>

        {/* Registration form */}
        <UserForm
          handleSubmit={handleSubmit}
          errors={errors}
        />

        {/* Login Link */}
        <Typography
          variant="body2"
          align="center"
          sx={{ mt: 3 }}
        >
          Already have an account?{" "}
          <Link to="/login">
            Login
          </Link>
        </Typography>
      </Paper>
    </Box>
  );
};

export default Register;