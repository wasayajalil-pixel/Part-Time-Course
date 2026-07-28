// Parent Card class
class Card {
  constructor(name, cost) {
    this.name = name;
    this.cost = cost;
  }
}

// Unit class inherits from Card
class Unit extends Card {
  constructor(name, cost, power, resilience) {
    super(name, cost);
    this.power = power;
    this.resilience = resilience;
  }

  // Attack another Unit
  attack(target) {
    if (target instanceof Unit) {
      // Reduce target's resilience by attacker's power
      target.resilience -= this.power;

      console.log(
        `${this.name} attacks ${target.name} for ${this.power} damage.`
      );

      console.log(
        `${target.name} now has ${target.resilience} resilience.`
      );
    } else {
      throw new Error("Target must be a Unit!");
    }
  }
}

// Effect class inherits from Card
class Effect extends Card {
  constructor(name, cost, text, stat, magnitude) {
    super(name, cost);
    this.text = text;
    this.stat = stat;
    this.magnitude = magnitude;
  }

  // Play the Effect on a Unit
  play(target) {
    if (target instanceof Unit) {
      // Increase or decrease power/resilience
      target[this.stat] += this.magnitude;

      console.log(
        `${this.name} changes ${target.name}'s ${this.stat} by ${this.magnitude}.`
      );
    } else {
      throw new Error("Target must be a Unit!");
    }
  }
}

// Create Units
const redBeltNinja = new Unit(
  "Red Belt Ninja",
  3,
  3,
  4
);

const blackBeltNinja = new Unit(
  "Black Belt Ninja",
  4,
  5,
  4
);

// Create Effects
const hardAlgorithm = new Effect(
  "Hard Algorithm",
  2,
  "Increase target's resilience by 3",
  "resilience",
  3
);

const unhandledPromiseRejection = new Effect(
  "Unhandled Promise Rejection",
  1,
  "Reduce target's resilience by 2",
  "resilience",
  -2
);

const pairProgramming = new Effect(
  "Pair Programming",
  3,
  "Increase target's power by 2",
  "power",
  2
);

// Play Hard Algorithm on Red Belt Ninja
hardAlgorithm.play(redBeltNinja);

// Play Unhandled Promise Rejection on Red Belt Ninja
unhandledPromiseRejection.play(redBeltNinja);

// Play Pair Programming on Red Belt Ninja
pairProgramming.play(redBeltNinja);

// Red Belt Ninja attacks Black Belt Ninja
redBeltNinja.attack(blackBeltNinja);