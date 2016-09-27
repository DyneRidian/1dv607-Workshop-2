package model;

import java.util.ArrayList;

public class Member {

	private String name;
	private String memberID;
	private String personalNumber;
	private int numberOfBoats;
	 
	ArrayList<Boat> boatList;
	 
	 public int getNumberOfBoats() {
		return numberOfBoats;
	}

	public void setNumberOfBoats(int numberOfBoats) {
		this.numberOfBoats = numberOfBoats;
	}

	public Member(String name, String personalNumber){
		 
		this.name = name;
		this.personalNumber = personalNumber;
		this.memberID = generateID();
		
		boatList = new ArrayList<Boat>();
		 
	 }
	 
	 private String generateID(){
		 
	
		 
		 return "Hello";
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
