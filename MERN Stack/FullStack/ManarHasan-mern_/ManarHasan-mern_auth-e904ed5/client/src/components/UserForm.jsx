import Box from "@mui/material/Box";
import InputText from "./Fields/InputText";
import { useState } from "react";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";

const UserForm = (props) => {
  const { handleSubmit, error } = props;
  const [firstName, setFirstName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  return (
    <form
      method="post"
      onSubmit={(e) =>
        handleSubmit(e, { firstName, email, password, confirmPassword })
      }
    >
      {error ? <Typography color="error">{error}</Typography> : null}
      <Box sx={{ m: 2 }}>
        <InputText
          id="firstName"
          name="firstName"
          onChange={(e) => setFirstName(e.target.value)}
          label="First Name"
          value={firstName}
          isError={firstName.length < 3}
        />
      </Box>
      <Box sx={{ m: 2 }}>
        <InputText
          id="email"
          name="email"
          type="email"
          onChange={(e) => setEmail(e.target.value)}
          label="Email"
          value={email}
          isError={email.length < 2 || error}
        />
      </Box>
      <Box sx={{ m: 2 }}>
        <InputText
          id="password"
          name="password"
          type="password"
          onChange={(e) => setPassword(e.target.value)}
          label="Password"
          value={password}
          isError={password.length < 2 || error}
        />
      </Box>
      <Box sx={{ m: 2 }}>
        <InputText
          id="confirmPassword"
          name="confirmPassword"
          type="password"
          onChange={(e) => setConfirmPassword(e.target.value)}
          label="Confirm Password"
          value={confirmPassword}
          isError={confirmPassword.length < 2 || error}
        />
      </Box>
      <Button type="submit" variant="contained" color="success">
        Submit
      </Button>
    </form>
  );
};

export default UserForm;
