const express = require("express");
const cors = require("cors");
const cookieParser = require("cookie-parser");
require("dotenv").config();

const app = express();

// Connect to MongoDB
require("./config/mongoose.config");

// Middleware

// Allows us to read JSON from req.body
app.use(express.json());

// Allows form data
app.use(express.urlencoded({ extended: true }));

// Allow React frontend to communicate with backend
app.use(
  cors({
    origin: "http://localhost:5173",
  })
);

// Allows us to read cookies
app.use(cookieParser());

// Routes AFTER middleware
// require("./routes/user.routes")(app);
// require("./routes/product.routes")(app);


const port = process.env.PORT || 8000;

app.listen(port, () => {
  console.log(`Server is running on: ${port}`);
});