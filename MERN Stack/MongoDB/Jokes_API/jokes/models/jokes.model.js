const mongoose = require("mongoose");

const JokeSchema = new mongoose.Schema(
  {
    setup: {
      type: String,
      required: [true, "The joke setup is required."],
      minlength: [5, "The setup must be at least 5 characters long."],
    },

    punchline: {
      type: String,
      required: [true, "The punchline is required."],
      minlength: [3, "The punchline must be at least 3 characters long."],
    },
  },
  {
    timestamps: true,
  },
);

const Joke = mongoose.model("joke", JokeSchema);

module.exports = Joke;