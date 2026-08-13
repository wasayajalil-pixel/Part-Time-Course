import { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

// Material UI
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import Alert from "@mui/material/Alert";

// Reuse our InputText component
import InputText from "../components/InputText";

const Login = () => {
  // Form states
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  // Store backend login error
  const [error, setError] = useState("");

  const navigate = useNavigate();

  // Handle login form
  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      // Send login information to backend
      const response = await axios.post(
        "http://localhost:8000/api/login",
        {
          email,
          password,
        },
        {
          // Important because JWT is stored in cookie
          withCredentials: true,
        }
      );

      console.log(response.data);

      // Remove previous errors
      setError("");

      // Go to dashboard
      navigate("/dashboard");

    } catch (error) {
      console.log(error);

      // Show backend error
      setError(
        error.response?.data?.message || "Login failed"
      );
    }
  };

  return (
    // Full page background
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
      {/* Login Card */}
      <Paper
        elevation={6}
        sx={{
          width: "100%",
          maxWidth: 450,
          p: 4,
          borderRadius: 3,
        }}
      >
        {/* Page Title */}
        <Typography
          variant="h4"
          align="center"
          fontWeight="bold"
          gutterBottom
        >
          Welcome Back
        </Typography>

        {/* Small description */}
        <Typography
          variant="body2"
          align="center"
          color="text.secondary"
          sx={{ mb: 3 }}
        >
          Login to your Product Manager account
        </Typography>

        {/* Login Form */}
        <Box
          component="form"
          onSubmit={handleLogin}
        >
          <Stack spacing={2}>

            {/* Backend Error */}
            {error && (
              <Alert severity="error">
                {error}
              </Alert>
            )}

            {/* Email */}
            <InputText
              id="email"
              name="email"
              type="email"
              label="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              isError={
                email.length > 0 &&
                email.length < 3
              }
            />

            {/* Password */}
            <InputText
              id="password"
              name="password"
              type="password"
              label="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              isError={
                password.length > 0 &&
                password.length < 8
              }
            />

            {/* Login Button */}
            <Button
              type="submit"
              variant="contained"
              size="large"
              fullWidth
              sx={{
                py: 1.3,
                mt: 1,
              }}
            >
              Login
            </Button>

          </Stack>
        </Box>

        {/* Register Link */}
        <Typography
          variant="body2"
          align="center"
          sx={{ mt: 3 }}
        >
          Don't have an account?{" "}
          <Link to="/register">
            Register
          </Link>
        </Typography>

      </Paper>
    </Box>
  );
};

export default Login;