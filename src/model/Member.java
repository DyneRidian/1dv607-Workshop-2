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
	
	public void addBoat(Boat boat, String length){
		
		boat.length = length;
		boat.ID = Integer.toString(boatList.size()+1);
		
		boatList.add(boat);
		
		numberOfBoats = Integer.toString(boatList.size());
		 
	 }
	
	public String numberOfBoats(){
		return numberOfBoats;
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
