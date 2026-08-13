const mongoose = require("mongoose");

const UserSchema = new mongoose.Schema(
  {
    firstName: {
      type: String,
      required: true,
      min: [2, "First name must be at least 3 characters long"],
    },
    email: {
      type: String,
      required: true,
    },
    age: {
      type: Number,
    },
    password: {
      type: String,
      required: true,
    },
  },
  { timestamps: true },
);

const User = mongoose.model("user", UserSchema);

module.exports = User;
