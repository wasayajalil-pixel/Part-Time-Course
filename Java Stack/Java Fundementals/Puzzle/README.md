# Puzzle Java

## Overview

Puzzle Java is a Java console application that demonstrates problem-solving using loops, arrays, ArrayLists, methods, and the `Random` class. The project generates random numbers, letters, and passwords while reinforcing object-oriented programming concepts.

This assignment was completed as part of the Java Fundamentals curriculum.

---

## Features

* Generate 10 random numbers between **1 and 20**
* Generate a random lowercase letter
* Generate a random 8-character password
* Generate multiple random passwords
* Shuffle an ArrayList into a random order (Bonus)

---

## Technologies Used

* Java
* Object-Oriented Programming (OOP)
* Java Collections (`ArrayList`)
* `java.util.Random`

---

## Project Structure

```
PuzzleJava/
│
├── PuzzleJava.java        // Contains all puzzle methods
├── TestPuzzleJava.java    // Tests every method
└── README.md
```

---

## Methods

### `getTenRolls()`

Generates an `ArrayList<Integer>` containing 10 random numbers between **1** and **20**.

**Example Output**

```
[5, 18, 2, 11, 7, 20, 4, 16, 9, 13]
```

---

### `getRandomLetter()`

Creates an array containing all 26 lowercase letters of the alphabet and returns one random letter.

**Example Output**

```
q
```

---

### `generatePassword()`

Uses `getRandomLetter()` to generate a random password consisting of 8 lowercase letters.

**Example Output**

```
azhjtpwe
```

---

### `getNewPasswordSet(int length)`

Generates an array of random passwords.

The size of the array is determined by the `length` parameter.

**Example**

```
getNewPasswordSet(5)
```

**Output**

```
[mkqzbxya, jhprtewu, xvbndkls, qwertyui, plmoknij]
```

---

### Bonus: `shuffleArray()`

Accepts an `ArrayList<Integer>` and randomly rearranges its elements by swapping values at random indexes.

**Before**

```
[1, 2, 3, 4, 5]
```

**After**

```
[4, 1, 5, 2, 3]
```

---

## How to Run

1. Clone the repository.

```
git clone <repository-url>
```

2. Open the project in your preferred Java IDE (Eclipse, IntelliJ IDEA, or VS Code).

3. Compile the project.

4. Run `TestPuzzleJava.java`.

---

## Sample Output

```
10 Random Rolls:
[12, 5, 19, 2, 8, 20, 4, 15, 7, 1]

Random Letter:
q

Random Password:
azhjtpwe

Password Set:
[mkqzbxya, jhprtewu, xvbndkls, qwertyui, plmoknij]

Before Shuffle:
[12, 5, 19, 2, 8, 20, 4, 15, 7, 1]

After Shuffle:
[20, 7, 12, 4, 2, 19, 1, 8, 15, 5]
```

---

## Concepts Practiced

* Java Classes and Objects
* Methods and Return Types
* Arrays
* ArrayLists
* Strings
* Loops
* Random Number Generation
* Method Reusability
* Basic Algorithms
* Swapping Elements
* Object-Oriented Programming Principles

---

## Learning Outcomes

After completing this project, you should understand how to:

* Generate random values using the `Random` class.
* Work with arrays and `ArrayList`.
* Create reusable methods.
* Build random password generators.
* Manipulate collections using loops.
* Shuffle data using simple swapping algorithms.
* Test Java methods using a separate driver class.

---

## Author

**Jalil Wasaya**

Java Fundamentals Assignment – AXSOS Academy

