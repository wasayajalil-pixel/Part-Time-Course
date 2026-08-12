const Author = require("../models/author.model");

// GET ALL AUTHORS
const getAll = async (req, res) => {
  try {
    const authors = await Author.find().sort({ name: 1 });

    res.status(200).json({ authors });
  } catch (error) {
    res.status(500).json({ message: "Something went wrong", error });
  }
};

// GET ONE AUTHOR
const getOne = async (req, res) => {
  try {
    const author = await Author.findById(req.params.id);

    if (!author) {
      return res.status(404).json({ message: "Author not found" });
    }

    res.status(200).json({ author });
  } catch (error) {
    res.status(404).json({ message: "Author not found" });
  }
};

// CREATE AUTHOR
const create = async (req, res) => {
  try {
    const author = await Author.create(req.body);

    res.status(201).json({ author });
  } catch (error) {
    res.status(400).json({ errors: error.errors });
  }
};

// UPDATE AUTHOR
const update = async (req, res) => {
  try {
    const author = await Author.findByIdAndUpdate(req.params.id, req.body, {
      new: true,
      runValidators: true,
    });

    if (!author) {
      return res.status(404).json({
        message: "Author not found",
      });
    }

    res.status(200).json({
      author,
    });
  } catch (error) {
    res.status(400).json({
      errors: error.errors,
    });
  }
};

// DELETE AUTHOR
const deleteA = async (req, res) => {
  try {
    const author = await Author.findByIdAndDelete(req.params.id);

    if (!author) {
      return res.status(404).json({
        message: "Author not found",
      });
    }

    res.status(200).json({
      message: "Author deleted successfully",
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
