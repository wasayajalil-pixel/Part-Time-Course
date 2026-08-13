const jwt = require("jsonwebtoken");


const authenticate = (req, res, next) => {

  // Get JWT from cookie
  const token = req.cookies.userToken;


  // No token = user is not logged in
  if (!token) {

    return res.status(401).json({
      message: "Unauthorized",
    });

  }


  try {

    // Verify token
    const decoded = jwt.verify(
      token,
      process.env.JWT_SECRET
    );


    // Save logged user's information in request
    req.user = decoded;


    // Continue to controller
    next();


  } catch (error) {

    return res.status(401).json({
      message: "Invalid token",
    });

  }

};


module.exports = authenticate;