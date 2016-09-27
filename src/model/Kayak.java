package model;

public class Kayak extends Boat {
	
	private int total = 0;
	
	public Kayak(int length){
		this.length = length;
	}

	@Override
	String boatType() {
		type = "Kayak";
		return type;
	}

	@Override
	int boatID() {return total++;}
	
}
