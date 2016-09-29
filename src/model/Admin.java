package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
	
	//test
	public static void main(String[] args) {
		
		Admin a = new Admin();
		a.populate();
		System.out.println(a.verboseList());

	}

	private int totalNumberOfMembers;
	private int totalNumberOfKayaks;
	private int totalNumberOfSailBoats;
	private int totalNumberOfMotorSailor;
	private int totalNumberOfOtherBoats;
	
	ArrayList<Member> memberStorage;
	
	/* this method right now is only changing the member list, but memberlist is empty
	 * everytime you start the program so its kinda shitty, unless we populate the memberlist everytime we start the program by reading
	 * the text file. but at that point maybe its just better to have no list and modify textfile directly.
	 */
	
	public Admin(){
		memberStorage = new ArrayList<>();
	}
	
	public ArrayList<Member> getMemberStorage(){
		return memberStorage;
	}
	
	public void populate(){
		//populating "memberStorage", "totalNumberOfMembers" and "total" for each boat.
		//substring() are used to avoid getting category names from the text files.

		try {
			//getting total number of members
			BufferedReader br = new BufferedReader(new FileReader("bin/model/generalInformation.txt"));
			Scanner line = new Scanner(br);
			totalNumberOfMembers = Integer.parseInt(line.nextLine().substring(16));
			totalNumberOfKayaks = Integer.parseInt(line.nextLine().substring(15));
			totalNumberOfSailBoats = Integer.parseInt(line.nextLine().substring(18));
			totalNumberOfMotorSailor = Integer.parseInt(line.nextLine().substring(20));
			totalNumberOfOtherBoats = Integer.parseInt(line.nextLine().substring(23));
			
			// Iterating over text files (each member is a text file) 
			for (int i = 0; i < getTotalNumberOfMembers(); i++) {

				String path = "bin/model/" + Integer.toString(1 + i) + ".txt";
				br = new BufferedReader(new FileReader(path));
				ArrayList<String> list = new ArrayList<String>();

				//load text file into a string
				line = new Scanner(br);
				while (line.hasNext()) {
					String str = line.nextLine();
					list.add(str);
				}
				// add member
				memberStorage.add(new Member(list.get(0).substring(5), list.get(1).substring(9),
						list.get(2).substring(15), list.get(3).substring(14)));
				// add each boat
					for(int j = 0; j < list.size()-4; j=j+4){
					if(list.get(5+j).substring(5).equals("Kayak")){memberStorage.get(0+i).boatList.add(new Kayak(list.get(6+j).substring(7),list.get(7+j).substring(3)));}
					else if(list.get(5+j).substring(5).equals("SailBoat")){memberStorage.get(0+i).boatList.add(new SailBoat(list.get(6+j).substring(7),list.get(7+j).substring(3)));}
					else if(list.get(5+j).substring(5).equals("MotorSailor")){memberStorage.get(0+i).boatList.add(new MotorSailor(list.get(6+j).substring(7),list.get(7+j).substring(3)));}
					else{memberStorage.get(0+i).boatList.add(new Other(list.get(6+j).substring(7),list.get(7+j).substring(3)));}
				}
					
				br.close();	
			}
			
			line.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getTotalNumberOfMembers(){
		
		return totalNumberOfMembers;
	}
	
	/* deleteMember now deletes element from array list and deletes that member's text file
	 * does not reduce number of members in generalInformation text file and causes the populate method to crash
	 * as it can't find the path to the deleted member's number, needs to be looked into :/
	 * 
	 * Careful when using at it will delete specified memeber's text file from bin/model.
	 */
	public void deleteMember(String memberID){
		
		for(int i = 0; i < memberStorage.size(); i++){
			
			if(memberStorage.get(i).getMemberID().equals(memberID)){
				
				memberStorage.remove(i);
				
			}
			
		}
		
		String path = "bin/model/" + memberID +  ".txt";
		
		File file = new File(path);
		
		if(file.exists()){
			
			file.delete();
			
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
