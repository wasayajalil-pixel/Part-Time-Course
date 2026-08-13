import { useState } from "react";
import UserForm from "../components/UserForm";
import axios from "axios";

const Registration = (props) => {
  const [error, setError] = useState("");

  const handleSubmit = async (e, data) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8000/api/user", data, {
        withCredentials: true,
      });
      setError("");
    } catch (error) {
      setError(error.response.data.error);
    }
  };

  return (
    <>
      <h1>Registration</h1>
      <UserForm handleSubmit={handleSubmit} error={error} />
    </>
  );
};

export default Registration;
