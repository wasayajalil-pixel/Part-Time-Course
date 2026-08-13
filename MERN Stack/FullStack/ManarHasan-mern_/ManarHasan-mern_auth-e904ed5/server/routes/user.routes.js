const UserController = require("../controllers/user.controller");

module.exports = (app) => {
  app.post("/api/user", UserController.registerUser);
  // app.post("/api/login", UserController.loginUser);
};
