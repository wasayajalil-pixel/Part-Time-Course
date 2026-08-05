const mongoose = require("mongoose");
const ur2 = "mongodb://wasayajalil_db_user:lXznDyyXgr0DIyA1@ac-cumeruq-shard-00-00.yymxhqm.mongodb.net:27017,ac-cumeruq-shard-00-01.yymxhqm.mongodb.net:27017,ac-cumeruq-shard-00-02.yymxhqm.mongodb.net:27017/?ssl=true&replicaSet=atlas-14dyyn-shard-0&authSource=admin&appName=Cluster0"

mongoose.connect(ur2)

    .then(() => console.log("Established a connection to the database"))

    .catch(err => console.log("Something went wrong when connecting to the database", err));