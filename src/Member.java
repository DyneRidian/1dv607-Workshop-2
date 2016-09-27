import java.util.ArrayList;

public class Member {

	private String name;
	private String memberID;
	private String personalNumber;
	 
	ArrayList<Boat> boatList;
	 
	 public Member(String name, String personalNumber){
		 
		this.name = name;
		this.personalNumber = personalNumber;
		this.memberID = generateID();
		 
	 }
	 
	 private String generateID(){
		 
		 String ID;
		 
		 
		 
		 return ID;
	 }
	 
	 public void addBoat(Boat boat){
		 
		 boatList.add(boat);
		 
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
