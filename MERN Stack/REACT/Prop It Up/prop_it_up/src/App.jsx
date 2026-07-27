import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import PersonCard from './PersonCard'

function App() {
   return (
    <div className="App">
      <PersonCard
        firstName="Jane"
        lastName="Doe"
        age={45}
        hairColor="Black"
      />

      <PersonCard
        firstName="John"
        lastName="Smith"
        age={88}
        hairColor="Brown"
      />

      <PersonCard
        firstName="Millard"
        lastName="Fillmore"
        age={50}
        hairColor="Brown"
      />

      <PersonCard
        firstName="Maria"
        lastName="Smith"
        age={62}
        hairColor="Blonde"
      />
    </div>
  );

}

export default App
