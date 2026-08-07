# Product Manager

## Description

Product Manager is a full-stack MERN application that allows users to manage products. Users can create, view, update, and delete products. The application uses React for the frontend, Express and Node.js for the backend, and MongoDB for storing data.

---

## Features

- View all products
- Create a new product
- View product details
- Update an existing product
- Delete a product from the Dashboard
- Delete a product from the Details page
- Redirect to Dashboard after deleting a product
- Back to Dashboard navigation

---

## Technologies Used

### Frontend
- React
- React Router DOM
- Axios
- Vite

### Backend
- Node.js
- Express.js
- MongoDB
- Mongoose

---

## Installation

### 1. Clone the repository

```bash
git clone <repository-url>
```

### 2. Install backend dependencies

```bash
cd server
npm install
```

### 3. Install frontend dependencies

```bash
cd client
npm install
```

---

## Environment Variables

Create a `.env` file inside the server folder.

```env
PORT=8000
MONGODB_URI=your_mongodb_connection_string
```

---

## Running the Project

### Start the backend

```bash
cd server
npm run dev
```

Backend runs on:

```
http://localhost:8000
```

### Start the frontend

```bash
cd client
npm run dev
```

Frontend runs on:

```
http://localhost:5173
```

---

## API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | /api/products | Get all products |
| GET | /api/products/:id | Get one product |
| POST | /api/products | Create product |
| PUT | /api/products/:id | Update product |
| DELETE | /api/products/:id | Delete product |

---

## Application Routes

| Route | Description |
|--------|-------------|
| /register | Add a new product |
| /dashboard | Display all products |
| /products/:id | Product details |
| /products/:id/edit | Edit product |

---

## Project Structure

```
client/
│
├── src/
│   ├── pages/
│   │   ├── Dashboard.jsx
│   │   ├── Details.jsx
│   │   ├── Edit.jsx
│   │   └── RegistrationForm.jsx
│   │
│   ├── App.jsx
│   └── main.jsx
│
server/
│
├── controllers/
├── models/
├── routes/
├── config/
├── server.js
└── package.json
```

---

## Screenshots

### Dashboard

Add a screenshot named:

```
dashboard.png
```

### Create Product

Add a screenshot named:

```
create.png
```

### Product Details

Add a screenshot named:

```
details.png
```

### Edit Product

Add a screenshot named:

```
edit.png
```

---

## Future Improvements

- Product search
- Product categories
- Product images
- User authentication
- Pagination
- Form validation messages

---

## Author

**Jalil Wasaya**

AXSOS Academy Full Stack MERN Bootcamp