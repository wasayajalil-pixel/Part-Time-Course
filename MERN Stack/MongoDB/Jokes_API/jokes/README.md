# Jokes API

## Description

This project is a simple REST API built with **Node.js**, **Express**, **MongoDB**, and **Mongoose**. It allows users to create, read, update, and delete jokes stored in a MongoDB database.

## Technologies Used

* Node.js
* Express
* MongoDB Atlas
* Mongoose
* Postman
* dotenv

## Project Structure

```text
jokes/
│
├── config/
│   └── mongoose.config.js
├── controllers/
│   └── jokes.controller.js
├── models/
│   └── jokes.model.js
├── routes/
│   └── jokes.routes.js
├── .env
├── package.json
└── server.js
```

## Installation

1. Clone the repository.

2. Install dependencies:

```bash
npm install
```

3. Create a `.env` file:

```env
PORT=8000
DB_NAME=jokes_db
```

4. Start the server:

```bash
npm run dev
```

or

```bash
npm start
```

The server will run on:

```
http://localhost:8000
```

## API Routes

| Method | Route            | Description       |
| ------ | ---------------- | ----------------- |
| GET    | `/api/jokes`     | Get all jokes     |
| GET    | `/api/jokes/:id` | Get one joke      |
| POST   | `/api/jokes`     | Create a new joke |
| PUT    | `/api/jokes/:id` | Update a joke     |
| DELETE | `/api/jokes/:id` | Delete a joke     |

### Bonus Route

| Method | Route               | Description       |
| ------ | ------------------- | ----------------- |
| GET    | `/api/jokes/random` | Get a random joke |

## Example Request

**POST** `/api/jokes`

```json
{
  "setup": "Why do programmers prefer dark mode?",
  "punchline": "Because light attracts bugs."
}
```

## Validation

The Joke model validates:

* `setup` is required.
* `setup` must be at least 5 characters.
* `punchline` is required.
* `punchline` must be at least 3 characters.

## Testing

All endpoints were tested using **Postman** by performing:

* GET
* POST
* PUT
* DELETE

to verify that all CRUD operations work correctly.

## Author

**Jalil Wasaya**
