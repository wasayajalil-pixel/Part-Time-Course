const mongoose = require("mongoose");
const PlayerSchema = new mongoose.Schema(
    {
        name: {
            type: String,
            required:[true,"Player is required"],
            minlength:[2,"Player name must be at least 2 characters"],
        },

        position : {
            type: String,
            default: "",
        },
         game1Status: {
      type: String,
      enum: ["Playing", "Not Playing", "Undecided"],
      default: "Undecided",
    },

    game2Status: {
      type: String,
      enum: ["Playing", "Not Playing", "Undecided"],
      default: "Undecided",
    },
      game3Status: {
      type: String,
      enum: ["Playing", "Not Playing", "Undecided"],
      default: "Undecided",
    },
  },
  {
    timestamps: true,
    }
)

const Player = mongoose.model("Player", PlayerSchema);

module.exports = Player;