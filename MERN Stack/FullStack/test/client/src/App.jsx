import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import { Route, Routes } from 'react-router-dom'
import RegistrationForm from './pages/RegistrationForm'
import Dashboard from './pages/Dashboard'
import ProfilePage from './pages/ProfilePage'

function App() {
 

  return (
    <>
      <Routes>
      <Route path="/" element={<RegistrationForm />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/profile/:id" element={<ProfilePage />} />
    </Routes>
    </>
  )
}

export default App
