package model;

public abstract class Boat {

	private String type;
	private String ID;
	private String length;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getID(){
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

}
