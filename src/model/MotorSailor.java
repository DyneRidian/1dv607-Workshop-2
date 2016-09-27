package model;

public class MotorSailor extends Boat {

	private int total = 0;

	@Override
	String boatType() {
		type = "Motor Sailor";
		return type;
	}

	@Override
	int boatID() {return total++;}
}
