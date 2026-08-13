import axios from "axios";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const ProfilePage = (props) => {
  const params = useParams();
  const [user, setUser] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [firstName, setFirstName] = useState("");
  const [email, setEmail] = useState("");
  const [age, setAge] = useState(0);

  const fetchUserById = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8000/api/users/${params.id}`,
      );
      setUser(response.data.user);
      setFirstName(response.data.user.firstName);
      setEmail(response.data.user.email);
      setAge(response.data.user.age);
    } catch (err) {
      console.log(err);
    }
  };

  const updateUser = async (e) => {
    e.preventDefault();

    try {
      await axios.put(`http://localhost:8000/api/users/${params.id}`, {
        firstName,
        email,
        age,
      });

      setUser({
        ...user,
        firstName: firstName,
        email: email,
        age: age,
      });
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    fetchUserById();
  }, []);

  return user ? (
    <div>
      <p>First Name: {user.firstName}</p>
      <p>Age: {user.age}</p>
      <p>Email: {user.email}</p>
      <form onSubmit={updateUser}>
        <div>
          <label htmlFor="firstName">First Name: </label>
          <input
            type="text"
            name="firstName"
            id="firstName"
            onChange={(e) => setFirstName(e.target.value)}
            value={firstName}
          />
        </div>
        <div>
          <label htmlFor="email">Email: </label>
          <input
            type="email"
            name="email"
            id="email"
            onChange={(e) => setEmail(e.target.value)}
            value={email}
          />
        </div>
        <div>
          <label htmlFor="age">Age: </label>
          <input
            type="number"
            name="age"
            id="age"
            onChange={(e) => setAge(e.target.value)}
            value={age}
          />
        </div>
        <input type="submit" value="updateUser" />
      </form>
    </div>
  ) : (
    <p>Loading...</p>
  );
};

export default ProfilePage;
