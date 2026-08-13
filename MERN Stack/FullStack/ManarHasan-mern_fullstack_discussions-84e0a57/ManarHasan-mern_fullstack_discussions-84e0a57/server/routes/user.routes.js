const UserController = require("../controllers/user.controller");

module.exports = (app) => {
  app.get("/api/health", (req, res) => {
    return res.json({ message: "backend is healthy" });
  });
  app.get("/api/users", UserController.findUsers);
  app.post("/api/users", UserController.createUser);
  app.get("/api/users/:id", UserController.getUserById);
  app.delete("/api/users/:id", UserController.deleteUser);
  app.put("/api/users/:id", UserController.updateUser);
};
