const mongoose = require("mongoose");

const uri = "mongodb+srv://wasayajalil_db_user:<lXznDyyXgr0DIyA1>@cluster0.yymxhqm.mongodb.net/?appName=Cluster0"

mongoose.connect(uri)

    .then(() => console.log("Established a connection to the database"))

    .catch(err => console.log("Something went wrong when connecting to the database", err));