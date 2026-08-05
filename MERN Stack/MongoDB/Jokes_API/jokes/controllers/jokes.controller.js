const Joke = require("../models/jokes.model");

// Get all jokes
module.exports.getAllJokes = (req, res) => {
  Joke.find()
    .then((allJokes) => {
      res.json(allJokes);
    })
    .catch((error) => {
      res.status(400).json(error);
    });
};

// Get one joke
module.exports.getOneJoke = (req, res) => {
  Joke.findOne({ _id: req.params.id })
    .then((oneJoke) => {
      res.json(oneJoke);
    })
    .catch((error) => {
      res.status(400).json(error);
    });
};

// Create a joke
module.exports.createJoke = (req, res) => {
  Joke.create(req.body)
    .then((newJoke) => {
      res.json(newJoke);
    })
    .catch((error) => {
      res.status(400).json(error);
    });
};

// Update a joke
module.exports.updateJoke = (req, res) => {
  Joke.findOneAndUpdate(
    { _id: req.params.id },
    req.body,
    {
      new: true,
      runValidators: true,
    }
  )
    .then((updatedJoke) => {
      res.json(updatedJoke);
    })
    .catch((error) => {
      res.status(400).json(error);
    });
};

// Delete a joke
module.exports.deleteJoke = (req, res) => {
  Joke.deleteOne({ _id: req.params.id })
    .then((result) => {
      res.json(result);
    })
    .catch((error) => {
      res.status(400).json(error);
    });
};