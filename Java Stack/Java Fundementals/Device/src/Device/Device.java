package Device;

public class Device {
//	Device variable
	protected int battery;
//	Constructor
	public Device() {
		this.battery = 100;
	}
	//Getter
	public int getDevice() {
		return battery;
		
	}
	//Setter
	public void setDevice() {
		this.battery = 100;
	}
	// Display Function
	public void displayBattery() {
		System.out.println("Remaining Battery:" + battery);
	}

}
