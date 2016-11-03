package model;

public class Kayak extends Boat {
	
	public Kayak(String length, String ID){
		this.type = "Kayak";
		this.length = length;
		this.ID = ID;
	}

	@Override
	String boatType() {
		return this.type;
	}
}


