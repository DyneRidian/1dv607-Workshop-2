package model;

public class SailBoat extends Boat {

	public SailBoat(String length, String ID){
		this.type = "SailBoat";
		this.length = length;
		this.ID = ID;
	}
	
	@Override
	String boatType() {
		return this.type;
	}
}
