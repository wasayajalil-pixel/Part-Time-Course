const JokeController = require("../controllers/jokes.controller");

module.exports = (app) => {
  // Get all jokes
  app.get("/api/jokes", JokeController.getAllJokes);

  // Get a random joke
  // This must be before /api/jokes/:id
  app.get("/api/jokes/random", JokeController.getRandomJoke);

  // Get one joke
  app.get("/api/jokes/:id", JokeController.getOneJoke);

  // Create one joke
  app.post("/api/jokes", JokeController.createJoke);

  // Update one joke
  app.put("/api/jokes/:id", JokeController.updateJoke);

  // You can also use PATCH
  app.patch("/api/jokes/:id", JokeController.updateJoke);

  // Delete one joke
  app.delete("/api/jokes/:id", JokeController.deleteJoke);
};