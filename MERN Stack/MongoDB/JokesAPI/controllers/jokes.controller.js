const Joke = require("../JokesAPI/models/jokes.model");

// Get all jokes
const getAllJokes = async (request, response) => {
  try {
    const allJokes = await Joke.find();

    return response.status(200).json({
      success: true,
      jokes: allJokes,
    });
  } catch (error) {
    return response.status(500).json({
      success: false,
      message: "Could not retrieve the jokes.",
      error: error.message,
    });
  }
};				

// Get one joke by ID
const getOneJoke = async (request, response) => {
  try {
    const joke = await Joke.findById(request.params.id);

    if (!joke) {
      return response.status(404).json({
        success: false,
        message: "Joke not found.",
      });
    }

    return response.status(200).json({
      success: true,
      joke: joke,
    });
  } catch (error) {
    return response.status(400).json({
      success: false,
      message: "Invalid joke ID.",
      error: error.message,
    });
  }
};

// Create a new joke
const createJoke = async (request, response) => {
  try {
    const newJoke = await Joke.create(request.body);

    return response.status(201).json({
      success: true,
      message: "Joke created successfully.",
      joke: newJoke,
    });
  } catch (error) {
    return response.status(400).json({
      success: false,
      message: "Joke validation failed.",
      errors: error.errors,
    });
  }
};

// Update one joke
const updateJoke = async (request, response) => {
  try {
    const updatedJoke = await Joke.findByIdAndUpdate(
      request.params.id,
      request.body,
      {
        new: true,
        runValidators: true,
      },
    );

    if (!updatedJoke) {
      return response.status(404).json({
        success: false,
        message: "Joke not found.",
      });
    }

    return response.status(200).json({
      success: true,
      message: "Joke updated successfully.",
      joke: updatedJoke,
    });
  } catch (error) {
    return response.status(400).json({
      success: false,
      message: "Could not update the joke.",
      errors: error.errors || error.message,
    });
  }
};

// Delete one joke
const deleteJoke = async (request, response) => {
  try {
    const deletedJoke = await Joke.findByIdAndDelete(request.params.id);

    if (!deletedJoke) {
      return response.status(404).json({
        success: false,
        message: "Joke not found.",
      });
    }

    return response.status(200).json({
      success: true,
      message: "Joke deleted successfully.",
      joke: deletedJoke,
    });
  } catch (error) {
    return response.status(400).json({
      success: false,
      message: "Could not delete the joke.",
      error: error.message,
    });
  }
};

module.exports = {
  getAllJokes,
  getOneJoke,
  createJoke,
  updateJoke,
  deleteJoke,
};
