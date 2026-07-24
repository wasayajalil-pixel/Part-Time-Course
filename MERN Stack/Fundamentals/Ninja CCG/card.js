//Card Class
class Card {
  constructor(name, cost) {
    this.name = name;
    this.cost = cost;
  }
}

//Unit Class
class Unit extends Card {
  constructor(name, cost, power, resilience) {
    super(name, cost);
    this.power = power;
    this.resilience = resilience;
  }
  attack(target) {
    // Check that the target is an instance of Unit.
    // Reduce the target's resilience by this Unit's power.
    if (target instanceof Unit) {
      target.resilience -= this.power;
      console.log(
        `${this.name} attacks ${target.name} for ${this.power} damage.`,
      );
      console.log(`${target.name} now has ${target.resilience} resilience.`);
      // Returning this allows method chaining if needed.
      return this;
      // Throw an error if the target is not a Unit.
    } else {
      throw new Error("Target must be a Unit");
    }
  }
}

//Effect Class
class Effect extends Card {
  constructor(name, cost, text, stat, magnitude) {
    super(name, cost);
    // Positive numbers increase the stat.
    // Negative numbers decrease the stat.
    this.magnitude = magnitude;
    // The Unit property that will change:
    // either "power" or "resilience".
    this.stat = stat;
    // A description of what the Effect card does.
    this.text = text;
  }

  play(target) {
    if (target instanceof Unit) {
      target[this.stat] += this.magnitude;
      console.log(`${this.name} played on ${target.name}`);
      console.log(this.text);
      console.log(`${target.name}'s ${this.stat} is now ${target[this.stat]}`);
      return this;
    } else {
      throw new Error("Target must be a unit");
    }
  }
}

// Create the Effect cards & Unit cards
const redBelt = new Unit("Red Belt", 3, 3, 4);
const blackBelt = new Unit("Black Belt", 4, 5, 4);
const hardAlgo = new Effect("Hard Algo",2,"Increase target's resilience by 3.","resilience",3);
const unhandledPromiseRejection = new Effect("Unhandled Promise Rejection",1,"Reduce target's resilience by 2.","resilience",-2);
const pairProgramming = new Effect("Pair Programming",3,"Increase target's power by 2.","power",2);

//test case

hardAlgo.play(redBelt);
unhandledPromiseRejection.play(redBelt);
pairProgramming.play(redBelt);
redBelt.attack(blackBelt);

//Display the info
console.log(redBelt);
console.log(blackBelt);

