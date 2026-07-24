class Ninja {
    constructor(name , health){
        this.name = name;
        this.health = 100;
        this.speed = 3;
        this.strength = 3;
    }

    sayName(){
        console.log(`my name is ${this.name}`);
    }
    
    showStatus(){
        console.log(`name : ${this.name} , strength : ${this.strength} , Speed : ${this.speed} `)
    }

    drinkShake(){
         this.health += 10;
        console.log(`health : ${this.health}`)
    }

    
}

const ninja1 = new Ninja("Hyabusa")
ninja1.sayName();
ninja1.showStatus();
ninja1.drinkShake();