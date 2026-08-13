import { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

// Material UI
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import Paper from "@mui/material/Paper";
import Stack from "@mui/material/Stack";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";

const Dashboard = () => {
  // Store all products from backend
  const [products, setProducts] = useState([]);

  // Used to redirect the user
  const navigate = useNavigate();

  // GET ALL PRODUCTS
  useEffect(() => {
    const getProducts = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8000/api/products",
          {
            withCredentials: true,
          }
        );

        console.log(response.data);

        setProducts(response.data.products);
      } catch (error) {
        console.log(error);
      }
    };

    getProducts();
  }, []);


  // DELETE PRODUCT
  const handleDelete = async (id) => {
    try {
      await axios.delete(
        `http://localhost:8000/api/products/${id}`,
        {
          withCredentials: true,
        }
      );

      // Remove product from screen without refreshing the page
      setProducts(
        products.filter(
          (product) => product._id !== id
        )
      );
    } catch (error) {
      console.log(error);
    }
  };

  // LOGOUT USER
  const handleLogout = async () => {
    try {
      // Send request to backend
      // Backend will remove JWT cookie
      await axios.post(
        "http://localhost:8000/api/logout",
        {},
        {
          withCredentials: true,
        }
      );

      // After logout go to login page
      navigate("/login");

    } catch (error) {
      console.log(error);
    }
  };

  return (
    <Box
      sx={{
        minHeight: "100vh",
        backgroundColor: "#f5f5f5",
      }}
    >
      {/* =========================
          NAVBAR
      ========================= */}
      <AppBar position="static">
        <Toolbar>

          {/* App Name */}
          <Typography
            variant="h6"
            fontWeight="bold"
            sx={{ flexGrow: 1 }}
          >
            Product Manager
          </Typography>

          {/* Navbar Buttons */}
          <Stack
            direction="row"
            spacing={1}
          >
            {/* Create Product */}
            <Button
              component={Link}
              to="/products/new"
              color="inherit"
            >
              Create Product
            </Button>

            {/* Logout */}
            <Button
              color="inherit"
              onClick={handleLogout}
            >
              Logout
            </Button>

          </Stack>

        </Toolbar>
      </AppBar>

      {/* =========================
          MAIN CONTENT
      ========================= */}
      <Box
        sx={{
          maxWidth: 1100,
          mx: "auto",
          p: 4,
        }}
      >

        {/* Header */}
        <Stack
          direction="row"
          justifyContent="space-between"
          alignItems="center"
          sx={{ mb: 3 }}
        >
          <Box>
            <Typography
              variant="h4"
              fontWeight="bold"
            >
              Dashboard
            </Typography>

            <Typography color="text.secondary">
              Manage all your products here.
            </Typography>
          </Box>

          <Button
            component={Link}
            to="/products/new"
            variant="contained"
          >
            + Add Product
          </Button>

        </Stack>

        {/* =========================
            PRODUCTS TABLE
        ========================= */}
        <TableContainer
          component={Paper}
          elevation={3}
          sx={{
            borderRadius: 3,
          }}
        >
          <Table>

            {/* Table Header */}
            <TableHead>
              <TableRow>

                <TableCell>
                  <strong>Product</strong>
                </TableCell>

                <TableCell>
                  <strong>Price</strong>
                </TableCell>

                <TableCell>
                  <strong>Description</strong>
                </TableCell>

                <TableCell align="center">
                  <strong>Actions</strong>
                </TableCell>

              </TableRow>
            </TableHead>

            {/* Table Body */}
            <TableBody>

              {products.map((product) => (
                <TableRow
                  key={product._id}
                  hover
                >

                  {/* Product Title */}
                  <TableCell>
                    {product.title}
                  </TableCell>

                  {/* Product Price */}
                  <TableCell>
                    ${product.price}
                  </TableCell>

                  {/* Product Description */}
                  <TableCell>
                    {product.description}
                  </TableCell>

                  {/* Actions */}
                  <TableCell align="center">

                    <Stack
                      direction="row"
                      spacing={1}
                      justifyContent="center"
                    >

                      {/* View */}
                      <Button
                        component={Link}
                        to={`/products/${product._id}`}
                        variant="outlined"
                        size="small"
                      >
                        View
                      </Button>

                      {/* Edit */}
                      <Button
                        component={Link}
                        to={`/products/${product._id}/edit`}
                        variant="contained"
                        size="small"
                      >
                        Edit
                      </Button>

                      {/* Delete */}
                      <Button
                        color="error"
                        variant="contained"
                        size="small"
                        onClick={() =>
                          handleDelete(product._id)
                        }
                      >
                        Delete
                      </Button>

                    </Stack>

                  </TableCell>

                </TableRow>
              ))}

            </TableBody>

          </Table>

          {/* No Products */}
          {products.length === 0 && (
            <Typography
              align="center"
              color="text.secondary"
              sx={{ p: 4 }}
            >
              No products found.
            </Typography>
          )}

        </TableContainer>

      </Box>
    </Box>
  );
};

export default Dashboard;