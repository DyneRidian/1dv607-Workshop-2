package model;

public abstract class Boat {

	protected String type;
	protected String ID;
	protected String length;
	
	abstract String boatType();
	
	public String getID(){
		return ID;
	}
}
