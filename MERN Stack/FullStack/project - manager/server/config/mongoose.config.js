const mongoose = require("mongoose")
require("dotenv").config();

//connect express with the database
mongoose
  .connect(process.env.MONGOOSE_URI)
  .then(() => {
    console.log("Connected to MongoDB");
  })
  .catch((err) => {
    console.log("Error connecting to DB:");
    console.log(err);
  });