const express = require("express");
const cors = require("cors");
require("dotenv").config();

const app = express();

// Connect to MongoDB
require("./config/mongoose.config");

// Middleware FIRST
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use(
  cors({
    origin: "http://localhost:5173",
  })
);

// Routes AFTER middleware
require("./routes/product.route")(app);

const port = process.env.PORT || 8000;

app.listen(port, () => {
  console.log(`Server is running on: ${port}`);
});