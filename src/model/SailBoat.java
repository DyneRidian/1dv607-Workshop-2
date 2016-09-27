package model;

public class SailBoat extends Boat {
	
	private int total = 0;

	@Override
	String boatType() {
		type = "Sail Boat";
		return type;
	}

	@Override
	int boatID() {return total++;}
}
