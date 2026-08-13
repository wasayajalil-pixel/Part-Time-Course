const ProductController =
  require("../controllers/product.controller");

const authenticate =
  require("../middleware/auth.middleware");


module.exports = (app) => {


  // Get all
  app.get(
    "/api/products",
    authenticate,
    ProductController.getAllProducts
  );


  // Create
  app.post(
    "/api/products",
    authenticate,
    ProductController.createProduct
  );


  // Get one
  app.get(
    "/api/products/:id",
    authenticate,
    ProductController.getOneProduct
  );


  // Update
  app.put(
    "/api/products/:id",
    authenticate,
    ProductController.updateProduct
  );


  // Delete
  app.delete(
    "/api/products/:id",
    authenticate,
    ProductController.deleteProduct
  );

};