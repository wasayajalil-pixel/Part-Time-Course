const mongoose = require("mongoose");
const bcrypt = require("bcrypt");


const UserSchema = new mongoose.Schema(
  {
    firstName: {
      type: String,

      // Field is required
      required: [true, "First name is required"],

      // Minimum characters
      minlength: [2, "First name must be at least 2 characters"],
    },


    lastName: {
      type: String,

      required: [true, "Last name is required"],

      minlength: [2, "Last name must be at least 2 characters"],
    },


    email: {
      type: String,

      required: [true, "Email is required"],

      // Email cannot be repeated
      unique: true,

      // Simple email validation
      match: [
        /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
        "Please enter a valid email",
      ],
    },


    password: {
      type: String,

      required: [true, "Password is required"],

      minlength: [8, "Password must be at least 8 characters"],
    },
  },

  // Automatically creates:
  // createdAt
  // updatedAt
  { timestamps: true }
);

UserSchema.virtual("confirmPassword")
  .get(() => this._confirmPassword)
  .set((value) => (this._confirmPassword = value));

  UserSchema.pre("validate", function () {

  if (this.password !== this.confirmPassword) {

    this.invalidate(
      "confirmPassword",
      "Password must match confirm password"
    );
  }
});

UserSchema.pre("save", async function () {
    // Convert password into hashed password
    this.password = await bcrypt.hash(this.password, 10);
});

const User = mongoose.model("User", UserSchema);

module.exports = User;
