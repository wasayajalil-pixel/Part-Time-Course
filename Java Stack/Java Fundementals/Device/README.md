# Device and Phone Assignment

## Overview

This project demonstrates **Inheritance** in Java by creating a `Device` class and a `Phone` class that extends it.

The phone can:

* Make calls (-5 battery)
* Play games (-20 battery)
* Charge (+50 battery)

Each action displays the remaining battery level.

## Class Structure

### Device

* Battery starts at 100.
* Contains `displayBattery()` method.

### Phone

Extends `Device` and includes:

* `makeCall()`
* `playGame()`
* `charge()`

### DeviceTest

Tests the application by:

* Making 3 calls
* Playing 2 games
* Charging once


## Example Output

```text
You make a call.
Remaining Battery:95

You make a call.
Remaining Battery:90

You make a call.
Remaining Battery:85

You play a game.
Remaining Battery:65

You play a game.
Remaining Battery:45

You charge the phone.
Remaining Battery:95
```

## Concepts Used

* Object-Oriented Programming (OOP)
* Inheritance
* Classes and Objects
* Constructors
* Access Modifiers
* Methods

## Author

Jalil Wasaya
