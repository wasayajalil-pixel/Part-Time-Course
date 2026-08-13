const Product = require("../models/product.model");


const createProduct = async (req, res) => {

  try {

    // Create product
    // Also save logged user's ID
    const product = await Product.create({

      ...req.body,

      createdBy: req.user.id,

    });


    res.status(201).json({
      product,
    });


  } catch (error) {

    res.status(400).json({
      errors: error.errors,
    });

  }

};

const getAllProducts = async (req, res) => {

  try {

    const products = await Product.find()
      .populate(
        "createdBy",
        "firstName lastName email"
      );


    res.status(200).json({
      products,
    });


  } catch (error) {

    res.status(500).json({
      message: "Problem getting products",
    });

  }

};

const getOneProduct = async (req, res) => {

  try {

    const product = await Product.findById(
      req.params.id
    );


    if (!product) {

      return res.status(404).json({
        message: "Product not found",
      });

    }


    res.status(200).json({
      product,
    });


  } catch (error) {

    res.status(400).json({
      message: "Problem getting product",
    });

  }

};

const updateProduct = async (req, res) => {

  try {

    const product =
      await Product.findByIdAndUpdate(

        req.params.id,

        req.body,

        {
          // Return updated product
          new: true,

          // Run mongoose validations
          runValidators: true,
        }

      );


    if (!product) {

      return res.status(404).json({
        message: "Product not found",
      });

    }


    res.status(200).json({
      product,
    });


  } catch (error) {

    res.status(400).json({
      errors: error.errors,
    });

  }

};

const deleteProduct = async (req, res) => {

  try {

    const product =
      await Product.findByIdAndDelete(
        req.params.id
      );


    if (!product) {

      return res.status(404).json({
        message: "Product not found",
      });

    }


    res.status(200).json({
      message: "Product deleted successfully",
    });


  } catch (error) {

    res.status(400).json({
      message: "Problem deleting product",
    });

  }

};

module.exports = {

  createProduct,

  getAllProducts,

  getOneProduct,

  updateProduct,

  deleteProduct,

};

