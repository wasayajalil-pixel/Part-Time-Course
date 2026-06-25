# Abstract Art Museum

This project demonstrates the use of Abstract Classes, Inheritance, Method Overriding, and Polymorphism in Java.

An abstract class called `Art` is created with common attributes shared by all artwork. Two subclasses, `Painting` and `Sculpture`, extend the `Art` class and provide their own implementation of the abstract method `viewArt()`.

The museum stores different types of artwork inside an `ArrayList<Art>`, demonstrating polymorphism by treating all artwork objects as instances of the parent class.

## Features
- Abstract `Art` class
- `Painting` and `Sculpture` subclasses
- Method overriding using `viewArt()`
- Polymorphism with `ArrayList<Art>`
- Display artwork information

## Classes

### Art
- title
- author
- description
- abstract method `viewArt()`

### Painting
- Extends `Art`
- Additional field: `paintType`

### Sculpture
- Extends `Art`
- Additional field: `material`

## Objects Created
### Paintings
1. Monalisa
2. Venus Rising
3. Immaculate Conception c. 1628

### Sculptures
1. David
2. The Thinker

## Concepts Practiced
- Abstract Classes
- Abstract Methods
- Inheritance
- Method Overriding
- Polymorphism
- ArrayLists
- Object-Oriented Programming (OOP)

## Author
Jalil Wasaya