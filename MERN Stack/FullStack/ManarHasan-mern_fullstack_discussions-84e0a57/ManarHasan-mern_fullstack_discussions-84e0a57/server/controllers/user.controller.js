const User = require("../models/user.model");

const findUsers = async (req, res) => {
  try {
    const users = await User.find();

    return res.json({ users });
  } catch (error) {
    console.log(error);
  }
};

const getUserById = async (req, res) => {
  try {
    const user = await User.findById(req.params.id);
    return res.json({ user });
  } catch (err) {
    console.log(err);
  }
};

const createUser = async (req, res) => {
  try {
    const body = req.body;

    const user = await User.create(body);

    return res.json({ user });
  } catch (err) {
    console.log(err);
  }
};

const updateUser = async (req, res) => {
  try {
    const user = await User.updateOne(
      { _id: req.params.id },
      { $set: req.body },
    );
    return res.json({ user });
  } catch (err) {
    console.log(err);
  }
};

const deleteUser = async (req, res) => {
  try {
    await User.deleteOne({ _id: req.params.id });
    return res.json({
      success: true,
      message: `User with id: ${req.params.id} was deleted`,
    });
  } catch (err) {
    console.log(err);
  }
};

module.exports = {
  findUsers,
  createUser,
  getUserById,
  deleteUser,
  updateUser,
};
