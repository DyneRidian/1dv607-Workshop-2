package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {

	private int totalNumberOfMembers;
	private int totalNumberOfKayaks;
	private int totalNumberOfSailBoats;
	private int totalNumberOfMotorSailor;
	private int totalNumberOfOtherBoats;
	
	ArrayList<Member> memberStorage;
	
	/* when admin is called it initializes an array to hold all members and their 
	 * information which will then be manipuluated by the user when they are executing functions in the program */
	public Admin(){
		memberStorage = new ArrayList<>();
	}
	
	// returns the array which holds all members and their information
	public ArrayList<Member> getMemberStorage(){
		return memberStorage;
	}
	
	// populates the memberStorage array by reading the text files in the resources folder
	public void populate(){
		//populating "memberStorage", "totalNumberOfMembers" and "total" for each boat.
		//substring() are used to avoid getting category names from the text files.

		try {
			//getting total number of members
			BufferedReader br = new BufferedReader(new FileReader("resources/generalInformation.txt"));
			Scanner line = new Scanner(br);
			totalNumberOfMembers = Integer.parseInt(line.nextLine().substring(16));
			totalNumberOfKayaks = Integer.parseInt(line.nextLine().substring(15));
			totalNumberOfSailBoats = Integer.parseInt(line.nextLine().substring(18));
			totalNumberOfMotorSailor = Integer.parseInt(line.nextLine().substring(20));
			totalNumberOfOtherBoats = Integer.parseInt(line.nextLine().substring(21));
			// count is the current position in the member arraylist
			int count = 0;
			int loopTimes = getTotalNumberOfMembers();
			// Iterating over text files (each member is a text file) 
			for (int i = 0; i < loopTimes; i++) {
				
				String path = "resources/" + Integer.toString(1 + i) + ".txt";
				
				File file = new File(path);
				if(file.exists()){
					
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
					for (int j = 0; j < list.size() - 5; j = j + 4) {
						
						if (list.get(6 + j).substring(5).equals("Kayak")) {
							memberStorage.get(0 + count).boatList
									.add(new Kayak(list.get(7 + j).substring(7), list.get(5 + j).substring(3)));
						} else if (list.get(6 + j).substring(5).equals("SailBoat")) {
							memberStorage.get(0 + count).boatList
									.add(new SailBoat(list.get(7 + j).substring(7), list.get(5 + j).substring(3)));
						} else if (list.get(6 + j).substring(5).equals("MotorSailor")) {
							memberStorage.get(0 + count).boatList
									.add(new MotorSailor(list.get(7 + j).substring(7), list.get(5 + j).substring(3)));
						} else {
							memberStorage.get(0 + count).boatList
									.add(new Other(list.get(7 + j).substring(7), list.get(5 + j).substring(3)));
						}
					}
					count++;
				}
				else{
					
					loopTimes++;
				}
				br.close();
			}
			
			line.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// addBoat will add a boat to a member specified by their ID
	public void addBoat(String memberID, Boat boatType, String length) throws IOException{
		
		String boatID = null;
		String newMemberBoats = null;
		
		/* searches the memberStorage array for the specified member ID then adds
		 *  the boat to their list while giving the boat a unique ID for that user
		 */
		for(int i = 0; i < memberStorage.size(); i++){
			
			if(memberStorage.get(i).getMemberID().equals(memberID)){
				
				memberStorage.get(i).addBoat(boatType, length);
				boatID = memberStorage.get(i).getBoatList().get(memberStorage.get(i).boatList.size()-1).getID();
				newMemberBoats = memberStorage.get(i).getNumberOfBoats();
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		String path = "resources/" + memberID +  ".txt";
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		Scanner line = new Scanner(br);
		
		/* find the specified member's text file in resources and updates the information in there */
		while(line.hasNextLine()){
			String text = line.nextLine();
			if(text.contains("numberOfBoats:")){
				text = "numberOfBoats:" + newMemberBoats;
			}
			sb.append(text);
			sb.append("\r\n");
		}
		
		br.close();
		line.close();
		
		// add the new boat to the specified member's text file
		sb.append("ID:" + boatID + "\r\n");
		sb.append("type:" + boatType.getType() + "\r\n");
		sb.append("length:" + length + "\r\n");
		sb.append("------------------------" + "\r\n");
		
		Writer generalWriter;
		try {
			generalWriter = new BufferedWriter((new FileWriter(path)));
			generalWriter.write(sb.toString());
			generalWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// update fields in admin class if boat type matches them
		if(boatType.getType().equals("Kayak")){
			
			totalNumberOfKayaks++;
		}
		else if(boatType.getType().equals("SailBoat")){
			
			totalNumberOfSailBoats++;
		}
		else if(boatType.getType().equals("MotorSailor")){
			
			totalNumberOfMotorSailor++;
		}
		else if(boatType.getType().equals("Other")){
			
			totalNumberOfOtherBoats++;
		}
		
		generalUpdate();
	}
	
	//help method for addBoat()
	public void helpAddBoat(String boatRegisterID, int choice, String boatRegisterLength){
		
		try {
		if (choice == 1) {
			
				addBoat(boatRegisterID, new Kayak(null, null), boatRegisterLength);
			} 

		 else if (choice == 2) {

			addBoat(boatRegisterID, new SailBoat(null, null), boatRegisterLength);

		} else if (choice == 3) {

			addBoat(boatRegisterID, new MotorSailor(null, null), boatRegisterLength);

		} else {

			addBoat(boatRegisterID, new Other(null, null), boatRegisterLength);
		}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	
	/* helper method which updates the general information text file that 
	 * contains number of members and boats. Used in multiple methods.
	 */
	private void generalUpdate(){
		StringBuilder sb = new StringBuilder();
		
		sb.setLength(0);
		sb.append("numberOfMembers:" + totalNumberOfMembers + "\r\n");
		sb.append("numberOfKayaks:" + totalNumberOfKayaks + "\r\n");
		sb.append("numberOfSailboats:" + totalNumberOfSailBoats + "\r\n");
		sb.append("numberOfMotorSailor:" + totalNumberOfMotorSailor + "\r\n");
		sb.append("numberOtherBoatTypes:" + totalNumberOfOtherBoats);
		
		
		Writer generalWriter;
		try {
			generalWriter = new BufferedWriter((new FileWriter("resources/generalInformation.txt")));
			generalWriter.write(sb.toString());
			generalWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/* addMember method creates a new text file and gives the member a 
	 * unique ID also updates memberStorage array to include newly added member */
	public void addMember(String name, String personalNumber){
		
		String ID = null;
		String path = null;
		File file = null;;
		
		// find available ID number for new member
		for(int i = 1; i > 0; i++){
			
			path = "resources/" + i +  ".txt";
			file = new File(path);		
			if(!file.exists()){
				ID = Integer.toString(i);
				break;
			}
			
		}
		
		// add new member to the memberStorage array
		Member newMember = new Member(name, ID, personalNumber, "0");
		
		memberStorage.add(newMember);
		
		totalNumberOfMembers++;
		
		
		/* write new text file to resources folder with new member's information */
		StringBuilder sb = new StringBuilder();
		
		sb.append("Name:" + name + "\r\n");
		sb.append("memberID:" + ID + "\r\n");
		sb.append("personalNumber:" + personalNumber + "\r\n");
		sb.append("numberOfBoats:" + "0" + "\r\n");
		sb.append("------------------------" + "\r\n");
		
		Writer memberWriter;
		try {
			memberWriter = new BufferedWriter((new FileWriter(path)));
			memberWriter.write(sb.toString());
			memberWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		generalUpdate();
		
	}
	
	public int getTotalNumberOfMembers(){
		
		return totalNumberOfMembers;
	}
	
	// method to delete a member's textfile from the resources folder and also remove them from memberStorage array
	public void deleteMember(String memberID){
		
		for(int i = 0; i < memberStorage.size(); i++){
			
			if(memberStorage.get(i).getMemberID().equals(memberID)){
				
				Member deletedMember = memberStorage.get(i);
				
				totalNumberOfMembers--;
				
				for(int j = 0; j < deletedMember.getBoatList().size(); j++){
					
					if(deletedMember.getBoatList().get(j).getType().equals("Kayak")){
						
						totalNumberOfKayaks--;
						
					}
					else if(deletedMember.getBoatList().get(j).getType().equals("MotorSailor")){
											
						totalNumberOfMotorSailor--;
											
					}
					else if(deletedMember.getBoatList().get(j).getType().equals("SailBoat")){
						
						totalNumberOfSailBoats--;
						
					}
					else if(deletedMember.getBoatList().get(j).getType().equals("Other")){
						
						totalNumberOfOtherBoats--;
						
					}
					
				}
				
				memberStorage.remove(i);
			}
			
		}
		
		String path = "resources/" + memberID +  ".txt";
		
		File file = new File(path);
		
		if(file.exists()){
			
			file.delete();
			
		}
		
		generalUpdate();
		
	}
	// method to change boat length in the member's textfile and storage array
	public void changeBoat(String memberID, String boatID, String length) {
		
		// searches memberStorage array for member ID specified then changes specified boats length
		for (int i = 0; i < memberStorage.size(); i++) {

			if (memberStorage.get(i).getMemberID().equals(memberID)) {

				Member selectedMember = memberStorage.get(i);

				for (int j = 0; j < selectedMember.getBoatList().size(); j++){
					
					if (selectedMember.getBoatList().get(j).getID().equals(boatID)){
						
						selectedMember.getBoatList().get(j).setLength(length);
						
					}
					
				}
				
			}
			
			// updates the boats length in the specified members textfile.
			try {

				String path = "resources/" + memberID + ".txt";

				File inFile = new File(path);

				if (!inFile.isFile()) {
					System.out.println("Parameter is not an existing file");
					return;
				}

				File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

				BufferedReader br = new BufferedReader(new FileReader(path));
				PrintWriter writer = new PrintWriter (new FileWriter(tempFile));

				String selectedLine = "ID:" + boatID;
				String currentLine = null;

				while ((currentLine = br.readLine()) != null) {
					String trimmedLine = currentLine.trim();
					if (trimmedLine.startsWith(selectedLine)) {
						for(int k = 0; k < 2; k++){
							writer.println(currentLine);
							currentLine = br.readLine();
						}
						currentLine = "length:" + length;
					}
				
					writer.println(currentLine);	
					
				}
				writer.close();
				br.close();

				// Delete the original file
				if (!inFile.delete()) {
					System.out.println("Could not delete file");
					return;
				}

				// Rename the new file to the filename the original file had.
				if (!tempFile.renameTo(inFile))
					System.out.println("Could not rename file");

			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}

			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	// remove boat from memberStorage array and from specified member's textfile
	public void deleteBoat(String memberID, String boatID) {

		String updateBoats = null;
		
		// searches memberStorage array for member then removes specified boat that matches boat ID
		for (int i = 0; i < memberStorage.size(); i++) {

			if (memberStorage.get(i).getMemberID().equals(memberID)) {

				Member selectedMember = memberStorage.get(i);

				for (int j = 0; j < selectedMember.getBoatList().size(); j++) {

					if (selectedMember.getBoatList().get(j).getID().equals(boatID)) {

						if (selectedMember.getBoatList().get(j).getType().equals("Kayak")) {

							totalNumberOfKayaks--;

						} else if (selectedMember.getBoatList().get(j).getType().equals("MotorSailor")) {

							totalNumberOfMotorSailor--;

						} else if (selectedMember.getBoatList().get(j).getType().equals("SailBoat")) {

							totalNumberOfSailBoats--;

						} else if (selectedMember.getBoatList().get(j).getType().equals("Other")) {

							totalNumberOfOtherBoats--;

						}
						memberStorage.get(i).setNumberOfBoats(Integer.toString(Integer.parseInt(memberStorage.get(i).getNumberOfBoats())-1));
						updateBoats = "numberOfBoats:" + memberStorage.get(i).getNumberOfBoats();
						memberStorage.get(i).getBoatList().remove(j);
						break;
					}
					
					updateBoats = "numberOfBoats:" + memberStorage.get(i).getNumberOfBoats();
					
				}
				
				// looks for member's textfile then carefully deletes neccessary lines that relate to specified boat
				try {

					String path = "resources/" + memberID + ".txt";

					File inFile = new File(path);

					if (!inFile.isFile()) {
						System.out.println("Parameter is not an existing file");
						return;
					}

					File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

					BufferedReader br = new BufferedReader(new FileReader(path));
					PrintWriter writer = new PrintWriter (new FileWriter(tempFile));

					String lineToRemove = "ID:" + boatID;
					
					String currentLine = null;

					while((currentLine = br.readLine()) != null) {
						
						String trimmedLine = currentLine.trim();
						
						if (trimmedLine.startsWith(lineToRemove)) {
							for(int k = 0; k < 3; k++){
								br.readLine();
							}
							currentLine = br.readLine();
						}
						
						if(currentLine != null){
							if(trimmedLine.startsWith("numberOfBoats:")){
								writer.println(updateBoats);
							}
							else{
								writer.println(currentLine);	
							}
						}
							
					}
					writer.close();
					br.close();
					
					generalUpdate();

					// Delete the original file
					if (!inFile.delete()) {
						System.out.println("Could not delete file");
						return;
					}

					// Rename the new file to the filename the original file had.
					if (!tempFile.renameTo(inFile))
						System.out.println("Could not rename file");

				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}

				catch (IOException ex) {
					ex.printStackTrace();
				}
			}

		}

	}
}

