// Parent Class
class Ninja {
  constructor(name) {
    this.name = name;
    this.health = 100;
    this.speed = 3;
    this.strength = 3;
  }

  sayName() {
    console.log(`my name is ${this.name}`);
  }

  showStatus() {
    console.log(
      `name : ${this.name} , strength : ${this.strength} , Speed : ${this.speed} `,
    );
  }

  drinkShake() {
    this.health += 10;
    console.log(`health : ${this.health}`);
  }
}
//Child Class
class Sensei extends Ninja {
  constructor(name) {
    super(name);
    this.speed = 10;
    this.strength = 10;
    this.health = 200;
    this.wisdom = 10;
  }
  speakWisdom() {
    this.drinkShake();
    console.log(
      "What one programmer can do in one month, two programmers can do in two months.",
    );
  }
}

const ninja1 = new Ninja("Hyabusa");
ninja1.sayName();
ninja1.showStatus();
ninja1.drinkShake();

const superSensei = new Sensei("Master Splinte");
superSensei.sayName();
superSensei.showStatus();
superSensei.speakWisdom();
