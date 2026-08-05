const express = require("express");
const app = express();

const JokeController = require("../controllers/jokes.controller");

module.exports = (app) => {
  // Get all jokes
  app.get("/api/jokes", JokeController.getAllJokes);

  // Get one joke
  app.get("/api/jokes/:id", JokeController.getOneJoke);

  // Create a joke
  app.post("/api/jokes", JokeController.createJoke);

  // Update a joke
  app.put("/api/jokes/:id", JokeController.updateJoke);

  // Delete a joke
  app.delete("/api/jokes/:id", JokeController.deleteJoke);
};