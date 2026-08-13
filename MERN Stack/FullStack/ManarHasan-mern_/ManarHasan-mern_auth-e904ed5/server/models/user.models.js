const mongoose = require("mongoose");
const bcrypt = require("bcrypt");

const UserSchema = new mongoose.Schema(
  {
    firstName: {
      type: String,
      required: [true, "First name is required"],
    },

    email: {
      type: String,
      required: [true, "Email is required"],
    },

    password: {
      type: String,
      required: [true, "Password is required"],
      minlength: [8, "Password must be at least 8 characters"],
    },
  },
  { timestamps: true },
);

UserSchema.virtual("confirmPassword").set(function (value) {
  this._confirmPassword = value;
});

UserSchema.pre("validate", function () {
  if (!this.isModified("password")) return;

  if (this.password !== this._confirmPassword) {
    this.invalidate("confirmPassword", "Passwords must match");
  }
});

UserSchema.pre("save", async function () {
  if (!this.isModified("password")) return;

  this.password = await bcrypt.hash(this.password, 10);
});

const User = mongoose.model("User", UserSchema);

module.exports = User;
