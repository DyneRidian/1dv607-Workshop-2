package model;

public class Other extends Boat{

	public Other(String length, String ID){
		this.type = "Unkown";
		this.length = length;
		this.ID = ID;
	}
	
	@Override
	String boatType() {
		this.type = "Other type of boat";
		return this.type;
	}
	
}
