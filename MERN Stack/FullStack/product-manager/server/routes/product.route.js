const ProductController = require("../controllers/product.controller");
module.exports = (app) => {
  app.get("/api/products", ProductController.findProducts);
  app.post("/api/products", ProductController.createProducts);
  app.get("/api/products/:id", ProductController.findOneProduct);
  app.put("/api/products/:id", ProductController.updateProduct);
  app.delete("/api/products/:id", ProductController.deleteProduct);
};
