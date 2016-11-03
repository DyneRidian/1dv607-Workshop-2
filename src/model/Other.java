package model;

public class Other extends Boat{

	public Other(String length, String ID){
		this.type = "Other";
		this.length = length;
		this.ID = ID;
	}
	
	@Override
	public String boatType() {
		return this.type;
	}
	
}
