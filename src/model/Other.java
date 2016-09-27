package model;

public class Other extends Boat{
	
	private int total = 0;

	@Override
	String boatType() {
		type = "Other type of boat";
		return type;
	}

	@Override
	int boatID() {return total++;}
	
}
