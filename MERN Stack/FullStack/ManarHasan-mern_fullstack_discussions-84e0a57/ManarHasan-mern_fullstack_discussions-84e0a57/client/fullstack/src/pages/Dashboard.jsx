import axios from "axios";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const Dashboard = (props) => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);

  const getUsers = async () => {
    setLoading(true);
    try {
      const response = await axios.get("http://localhost:8000/api/users");

      setUsers(response.data.users);
    } catch (err) {
      console.log(err);
    } finally {
      setLoading(false);
    }
  };

  const onDelete = async (event, id) => {
    try {
      // http method: DELETE
      await axios.delete(`http://localhost:8000/api/users/${id}`);

      setUsers(
        users.filter((user) => {
          if (user._id !== id) {
            return user;
          }
        }),
      );
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getUsers();
  }, []);

  if (loading) {
    return <p style={{ textAlign: "center", fontSize: "40px" }}>Loading...</p>;
  }

  return (
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>First Name</th>
          <th>Email</th>
          <th>Age</th>
          <th>Password</th>
        </tr>
      </thead>
      <tbody>
        {users.map((user) => {
          return (
            <tr key={user._id}>
              <td>{user._id}</td>
              <td>{user.firstName}</td>
              <td>{user.email}</td>
              <td>{user.age}</td>
              <td>{user.password}</td>
              <td>
                <Link to={`/profile/${user._id}`}>View Profile</Link>
              </td>
              <td>
                <button onClick={(e) => onDelete(e, user._id)}>Delete</button>
              </td>
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};

export default Dashboard;
