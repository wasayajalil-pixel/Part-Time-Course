# ZooKeeper Mammal Assignment

## Description
This Java project tracks the energy levels of mammals in a zoo.

The project contains a superclass called `Mammal`, and two subclasses:
- `Gorilla`
- `Bat`

Each mammal has an energy level and can display its current energy.

## Classes

### Mammal
The `Mammal` class has:
- `energy` attribute with default value of 100
- `displayEnergy()` method that prints and returns the current energy level

### Gorilla
The `Gorilla` class extends `Mammal`.

Methods:
- `throwSomething()` decreases energy by 5
- `eatBananas()` increases energy by 10
- `climb()` decreases energy by 10

### Bat
The `Bat` class extends `Mammal`.

The bat starts with energy level 300.

Methods:
- `fly()` decreases energy by 50
- `eatHumans()` increases energy by 25
- `attackTown()` decreases energy by 100

## Test
The test file creates one Gorilla and one Bat.

### Gorilla Test
The gorilla:
- Throws something 3 times
- Eats bananas 2 times
- Climbs once
- Displays energy

Final Gorilla energy:

100 - 15 + 20 - 10 = 95

### Bat Test
The bat:
- Attacks town 3 times
- Eats humans 2 times
- Flies 2 times
- Displays energy

Final Bat energy:

300 - 300 + 50 - 100 = -50