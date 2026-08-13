const User = require("../models/user.model");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");

const register = async (req, res) => {
console.log("jhfgjhg")
  try {

    // Create user from information sent from React
    const user = await User.create(req.body);


    // Create JWT token
    const token = jwt.sign(
      {
        id: user._id,
      },

      process.env.JWT_SECRET,

      {
        expiresIn: "1d",
      }
    );


    // Send token inside cookie
    res
      .cookie("userToken", token, {
        httpOnly: true,
      })
      .status(201)
      .json({
        message: "User registered successfully",
        user,
      });


  } catch (error) {

    console.log(error)
    // MongoDB validation errors
    res.status(400).json({
      errors: error.errors,
    });

  }

};

const login = async (req, res) => {

  try {

    // Find user using email
    const user = await User.findOne({
      email: req.body.email,
    });


    // If email does not exist
    if (!user) {

      return res.status(400).json({
        message: "Invalid email or password",
      });

    }


    // Compare entered password with hashed password
    const correctPassword = await bcrypt.compare(
      req.body.password,
      user.password
    );


    // Wrong password
    if (!correctPassword) {

      return res.status(400).json({
        message: "Invalid email or password",
      });

    }


    // Create JWT token
    const token = jwt.sign(
      {
        id: user._id,
      },

      process.env.JWT_SECRET,

      {
        expiresIn: "1d",
      }
    );


    // Put JWT in cookie
    res
      .cookie("userToken", token, {
        httpOnly: true,
      })
      .status(200)
      .json({
        message: "Login successful",
        user,
      });


  } catch (error) {

    res.status(500).json({
      message: "Login error",
    });

  }

};

const logout = (req, res) => {

  res.clearCookie("userToken");

  res.status(200).json({
    message: "Logged out successfully",
  });

};

const getLoggedUser = async (req, res) => {

  try {

    const user = await User.findById(req.user.id);

    res.status(200).json({
      user,
    });

  } catch (error) {

    res.status(400).json({
      message: "Could not get logged user",
    });

  }

};

module.exports = { 
    getLoggedUser,
    logout,
    login,
    register,

}