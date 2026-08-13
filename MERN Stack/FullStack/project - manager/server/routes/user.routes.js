const UserController = require("../controllers/user.controller");

const authenticate = require("../middleware/auth.middleware");


module.exports = (app) => {

  // Register
  app.post(
    "/api/register",
    UserController.register
  );


  // Login
  app.post(
    "/api/login",
    UserController.login
  );


  // Logout
  app.post(
    "/api/logout",
    UserController.logout
  );


  // Protected route
  app.get(
    "/api/users/logged",
    authenticate,
    UserController.getLoggedUser
  );

};