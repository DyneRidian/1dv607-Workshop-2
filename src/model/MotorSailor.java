package model;

public class MotorSailor extends Boat {
	
	public MotorSailor(String length, String ID){
		this.type = "MotorSailor";
		this.length = length;
		this.ID = ID;
	}

	@Override
	String boatType() {
		return this.type;
	}

}