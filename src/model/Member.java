package model;

import java.util.ArrayList;

public class Member {

	private String name;
	private String memberID;
	private String personalNumber;
	private String numberOfBoats;
	 
	ArrayList<Boat> boatList;
	
	 public String getNumberOfBoats() {
		return numberOfBoats;
	}

	public void setNumberOfBoats(String numberOfBoats) {
		this.numberOfBoats = numberOfBoats;
	}

	public Member(String name,String ID, String personalNumber, String numberOfBoats){
		 
		this.name = name;
		this.memberID = ID;
		this.personalNumber = personalNumber;
		this.numberOfBoats = numberOfBoats;
		
		boatList = new ArrayList<Boat>();
		 
	 }
	
	public void addBoat(Boat boat){
		 
		 boatList.add(boat);
		 
	 }
	
	public int numberOfBoats(){
		return boatList.size();
	}
	
	public ArrayList<Boat> getBoatSize() {
        return boatList;
    }

	public String getName() {
		return name;
	}

	public String getMemberID() {
		return memberID;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public ArrayList<Boat> getBoatList() {
		return boatList;
	}
	
	 
}
