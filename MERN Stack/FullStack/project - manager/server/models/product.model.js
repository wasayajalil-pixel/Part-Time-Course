const mongoose = require("mongoose");


const ProductSchema = new mongoose.Schema(
  {

    title: {
      type: String,

      required: [true, "Title is required"],

      minlength: [
        3,
        "Title must be at least 3 characters"
      ],
    },


    price: {
      type: Number,

      required: [true, "Price is required"],

      min: [
        1,
        "Price must be greater than 0"
      ],
    },


    description: {
      type: String,

      required: [true, "Description is required"],

      minlength: [
        5,
        "Description must be at least 5 characters"
      ],
    },


    // Which user created this product?
    createdBy: {
      type: mongoose.Schema.Types.ObjectId,

      ref: "User",

      required: true,
    },

  },

  { timestamps: true }
);


const Product = mongoose.model(
  "Product",
  ProductSchema
);


module.exports = Product;