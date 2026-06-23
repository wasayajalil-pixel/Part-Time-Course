package Zookeeper;

public class Gorilla extends Mammal {
//	Gorilla Throw SomeThing Function decrease 5 energy
	public void ThrowSomething() {
		System.out.println("The gorilla has thrown something!");
		energy -= 5;
		Display();
	}
//  Gorilla Eat Bananas Function increase 10 energy
	public void EatBananas() {
		System.out.println("the gorilla's level of satisfaction");
		energy +=10;
		Display();
	}
//  Gorilla Climb Function decrease 10 energy
	public void Climb() {
		 System.out.println("The gorilla has climbed a tree");
	        energy -= 10;
	        Display();
	}

}
