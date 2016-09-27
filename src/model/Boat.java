package model;

public abstract class Boat {

	protected String type;
	protected int ID;
	protected int length;
	
	abstract String boatType();
	abstract int boatID();

}
