package Zookeeper;

public class ZookeeperTest {
	public static void main(String[] args) {
//		Create Gorilla Object
		Gorilla gorilla = new Gorilla();
		gorilla.ThrowSomething();
		gorilla.ThrowSomething();
		gorilla.ThrowSomething();
		gorilla.EatBananas();
		gorilla.EatBananas();
		gorilla.Climb();
		
		System.out.println("------------------------");

//		Create Bat Object
		Bat bat = new Bat();
		bat.AttackTown();
		bat.AttackTown();
		bat.AttackTown();
		bat.EatHuman();
		bat.EatHuman();
		bat.Fly();
		bat.Fly();
	}

}
