import { useState } from "react";

import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Stack from "@mui/material/Stack";

import InputText from "./InputText";

const UserForm = (props) => {
  // Receive handleSubmit and backend validation errors
  const { handleSubmit, errors = {} } = props;

  // Form states
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  return (
    <Box
      component="form"
      method="post"
      onSubmit={(e) =>
        handleSubmit(e, {
          firstName,
          lastName,
          email,
          password,
          confirmPassword,
        })
      }
    >
      {/* Stack gives equal spacing between inputs */}
      <Stack spacing={2}>

        {/* First Name */}
        <Box>
          <InputText
            id="firstName"
            name="firstName"
            label="First Name"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            isError={
              (firstName.length > 0 && firstName.length < 3) ||
              !!errors.firstName
            }
          />

          {errors.firstName && (
            <Typography
              color="error"
              variant="caption"
            >
              {errors.firstName.message}
            </Typography>
          )}
        </Box>

        {/* Last Name */}
        <Box>
          <InputText
            id="lastName"
            name="lastName"
            label="Last Name"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            isError={
              (lastName.length > 0 && lastName.length < 3) ||
              !!errors.lastName
            }
          />

          {errors.lastName && (
            <Typography
              color="error"
              variant="caption"
            >
              {errors.lastName.message}
            </Typography>
          )}
        </Box>

        {/* Email */}
        <Box>
          <InputText
            id="email"
            name="email"
            type="email"
            label="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            isError={
              (email.length > 0 && email.length < 3) ||
              !!errors.email
            }
          />

          {errors.email && (
            <Typography
              color="error"
              variant="caption"
            >
              {errors.email.message}
            </Typography>
          )}
        </Box>

        {/* Password */}
        <Box>
          <InputText
            id="password"
            name="password"
            type="password"
            label="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            isError={
              (password.length > 0 && password.length < 8) ||
              !!errors.password
            }
          />

          {errors.password && (
            <Typography
              color="error"
              variant="caption"
            >
              {errors.password.message}
            </Typography>
          )}
        </Box>

        {/* Confirm Password */}
        <Box>
          <InputText
            id="confirmPassword"
            name="confirmPassword"
            type="password"
            label="Confirm Password"
            value={confirmPassword}
            onChange={(e) =>
              setConfirmPassword(e.target.value)
            }
            isError={
              confirmPassword.length > 0 &&
              confirmPassword !== password
            }
          />

          {confirmPassword.length > 0 &&
            confirmPassword !== password && (
              <Typography
                color="error"
                variant="caption"
              >
                Passwords do not match
              </Typography>
            )}
        </Box>

        {/* Register Button */}
        <Button
          type="submit"
          variant="contained"
          size="large"
          fullWidth
          sx={{
            mt: 1,
            py: 1.3,
          }}
        >
          Register
        </Button>

      </Stack>
    </Box>
  );
};

export default UserForm;