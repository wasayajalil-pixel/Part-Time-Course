package Zookeeper;

public class Mammal {
	//Mammal VAriable
	protected int energy;
	//Constructor
	public Mammal(){
		this.energy = 100;
	}
	//Display Function
	public void Display() {
		System.out.println("Energy Level :" + this.energy);
	}
}