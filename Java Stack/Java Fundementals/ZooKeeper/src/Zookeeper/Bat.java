package Zookeeper;

public class Bat extends Mammal {
//	Constructor
	public Bat(){
		energy =300;
	}
//	fly Function decrease 50 energy
	public void Fly() {
		System.out.println("the bat is now airborne.");
		energy -= 50;
		Display();
	}
//	Eat Human Function Bat increase 25 energy
	public void EatHuman() {
		System.out.println("The bat is satisfied after eating humans");
		energy += 25;
		Display();
	}
//	Attack Town Function Bat Decrease 100 energy
	public void AttackTown() {
		System.out.println("The bat attacked a town!");
        energy -= 100;
        Display();
	}

}
