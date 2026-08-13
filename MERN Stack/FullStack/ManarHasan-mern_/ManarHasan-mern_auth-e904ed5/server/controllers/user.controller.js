const User = require("../models/user.models");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
require("dotenv").config();

const registerUser = async (req, res) => {
  try {
    const user = await User.create(req.body);

    const token = jwt.sign(
      { email: user.email, id: user._id },
      process.env.SECRET,
    );

    return res.cookie("jwt", token, { httpOnly: true }).json({ user });
  } catch (error) {
    return res.status(400).json({ error: error.message });
  }
};

const loginUser = async (req, res) => {
  try {
    const user = await User.findOne({ email: req.body.email });

    if (!user) {
      return res.status(400).json({ message: "Email does not exist " });
    }

    const isValid = bcrypt.compare(req.body.password, user.password);

    if (!isValid) {
      return res.status(400).json({ message: "Invalid password" });
    }

    const token = jwt.sign(
      { email: user.email, id: user._id },
      process.env.SECRET,
    );

    return res.cookie("jwt", token, { httpOnly: true }).json({ user });
  } catch (err) {}
};

module.exports = {
  registerUser,
};
