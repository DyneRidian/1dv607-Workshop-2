package model;

import java.util.ArrayList;

public class Admin {

	ArrayList<Member> memberStorage;
	
	/* this method right now is only changing the member list, but memberlist is empty
	 * everytime you start the program so its kinda shitty, unless we populate the memberlist everytime we start the program by reading
	 * the text file. but at that point maybe its just better to have no list and modify textfile directly.
	 */
	
	public Admin(Member member){
		memberStorage = new ArrayList<>();
		memberStorage.add(member);
	}
	
	public void deleteMember(String memberID){
		
		for(int i = 0; i < memberStorage.size(); i++){
			
			if(memberStorage.get(i).getMemberID() == memberID){
				
				memberStorage.remove(i);
			}
			
		}
		
	}
	
	public String verboseList(){
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < memberStorage.size(); i++){
			
			sb.append("Name: " + memberStorage.get(i).getName() + "\n");
			sb.append("Personal Number: " + memberStorage.get(i).getPersonalNumber() + "\n");
			sb.append("Member ID: " + memberStorage.get(i).getMemberID() + "\n");
			for(int j = 0; j < memberStorage.get(i).getBoatList().size(); j++){
				
				sb.append("Boat Type: " + memberStorage.get(i).getBoatList().get(j).boatType() + "\n");
				sb.append("Boat Length: " + memberStorage.get(i).getBoatList().get(j).length + "\n");
				
			}
			sb.append("-------------------------------------------- \n");

		}
		
		return sb.toString();	
	}

	public String compactList(){

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < memberStorage.size(); i++){
			
			sb.append("Name: " + memberStorage.get(i).getName() + "\n");
			sb.append("Member ID: " + memberStorage.get(i).getMemberID() + "\n");
			sb.append("Number of Boats: " + memberStorage.get(i).numberOfBoats() + "\n");
			

		}

		return sb.toString();		
	}
}
