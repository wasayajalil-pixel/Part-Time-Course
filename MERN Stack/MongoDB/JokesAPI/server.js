require("dotenv").config();

const express = require("express");

const app = express();
const port = process.env.PORT || 8000;

// Connect to MongoDB
require("./config/mongoose.config");

// Allow Express to read JSON data
app.use(express.json());

// Allow Express to read form data
app.use(express.urlencoded({ extended: true }));

// Add the joke routes
require("./routes/jokes.routes")(app);

// Test route
app.get("/", (request, response) => {
  response.json({
    message: "Welcome to the Jokes API!",
  });
});

// Start the server
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});