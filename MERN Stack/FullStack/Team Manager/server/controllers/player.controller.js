
const Player = require("../models/player.model");

// GET ALL 
const getAll = async (req, res) => {
  try {
    const players = await Player.find().sort({ name: 1 });

    res.status(200).json({ players });
  } catch (error) {
    res.status(500).json({ message: "Something went wrong", error });
  }
};

// GET ONE 
const getOne = async (req, res) => {
  try {
    const player = await Player.findById(req.params.id);

    if (!player) {
      return res.status(404).json({ message: "Player not found" });
    }

    res.status(200).json({ player });
  } catch (error) {
    res.status(404).json({ message: "Player not found" });
  }
};

// CREATE 
const create = async (req, res) => {
  try {
    const player = await Player.create(req.body);

    res.status(201).json({ player });
  } catch (error) {
    res.status(400).json({ errors: error.errors });
  }
};

// UPDATE 
const update = async (req, res) => {
  try {
    const player = await Player.findByIdAndUpdate(req.params.id, req.body, {
      new: true,
      runValidators: true,
    });

    if (!player) {
      return res.status(404).json({
        message: "Player not found",
      });
    }

    res.status(200).json({
      player,
    });
  } catch (error) {
    res.status(400).json({
      errors: error.errors,
    });
  }
};

// DELETE 
const deleteA = async (req, res) => {
  try {
    const player = await Author.findByIdAndDelete(req.params.id);

    if (!player) {
      return res.status(404).json({
        message: "Player not found",
      });
    }

    res.status(200).json({
      message: "Player deleted successfully",
    });
  } catch (error) {
    res.status(500).json({
      message: "Something went wrong",
    });
  }
};

module.exports = {
  getAll,
  getOne,
  create,
  update,
  deleteA,
};
