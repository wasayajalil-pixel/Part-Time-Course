const Product = require('../models/product.model');

// Get all products
const findProducts = async (req ,res) =>{
    try{
        const products = await Product.find();
        res.status(200).json({products})
    }    
    catch(error){console.log(error)}
}

// Create a new product
const createProducts = async (req,res) => {
    try{
        const body = req.body;
        const products = await Product.create(req.body);
        res.status(200).json({products});
    }
    catch(error){console.log(error)}
}

// get one product
const findOneProduct = async (req,res) => {
    try {
        const product = await Product.findById(req.params.id);
        res.status(200).json({ product })
    }
    catch(error){console.log(error)}
}

//update one product
const updateProduct = async (req,res) =>{
    try {
        const product = await Product.findByIdAndUpdate(req.params.id,req.body);
        res.status(200).json({product});
    }
    catch(error){
        res.status(409).json({ 
            success: false, 
            error: true, 
            message: "There is a problem updating" })
    }
}

// Delete one product
const deleteProduct = async (req, res) => {
  try {
    const product = await Product.findByIdAndDelete(req.params.id);

    res.status(200).json({ product });
  } catch (error) {
    res.status(400).json(error);
  }
};

module.exports = {
    findProducts,
    createProducts,
    findOneProduct,
    updateProduct,
    deleteProduct
}



