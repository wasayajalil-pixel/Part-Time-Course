const mongoose = require("mongoose");
const ProductSchema = new mongoose.Schema({
  title: {
    type: String,
    required: true,
    minlength: [3, "Title must be at least 3 characters"],
  },
  price: {
    type: Number,
    required: true,
    minlength: [0, "Price cannot be negative"],
  },
  description: {
    type: String,
    required: [true, "Product description is required"],
    minlength: [5, "Description must be at least 5 characters"],
  },
},
  {
    timestamps:true,
  }
);

const Product = mongoose.model("product",ProductSchema)
module.exports = Product;
