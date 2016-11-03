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
			BufferedReader br = new BufferedReader(new FileReader("src/resources/generalInformation.txt"));
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
				
				String path = "src/resources/" + Integer.toString(1 + i) + ".txt";
				
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

	public void addBoat(String memberID, Boat boatType, String length) throws IOException{
		
		String boatID = null;
		String newMemberBoats = null;
		
		for(int i = 0; i < memberStorage.size(); i++){
			
			if(memberStorage.get(i).getMemberID().equals(memberID)){
				
				memberStorage.get(i).addBoat(boatType, length);
				boatID = memberStorage.get(i).getBoatList().get(memberStorage.get(i).boatList.size()-1).ID;
				newMemberBoats = memberStorage.get(i).getNumberOfBoats();
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		String path = "src/resources/" + memberID +  ".txt";
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		Scanner line = new Scanner(br);
		
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
	
		sb.append("ID:" + boatID + "\r\n");
		sb.append("type:" + boatType.type + "\r\n");
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

		if(boatType.type.equals("Kayak")){
			
			totalNumberOfKayaks++;
		}
		else if(boatType.type.equals("SailBoat")){
			
			totalNumberOfSailBoats++;
		}
		else if(boatType.type.equals("MotorSailor")){
			
			totalNumberOfMotorSailor++;
		}
		else if(boatType.type.equals("Other")){
			
			totalNumberOfOtherBoats++;
		}
		
		generalUpdate();
		
	}
	
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
			generalWriter = new BufferedWriter((new FileWriter("src/resources/generalInformation.txt")));
			generalWriter.write(sb.toString());
			generalWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addMember(String name, String personalNumber){
		
		String ID = null;
		String path = null;
		File file = null;;
		
		for(int i = 1; i > 0; i++){
			
			path = "src/resources/" + i +  ".txt";
			file = new File(path);		
			if(!file.exists()){
				ID = Integer.toString(i);
				break;
			}
			
		}
		
		Member newMember = new Member(name, ID, personalNumber, "0");
		
		memberStorage.add(newMember);
		
		totalNumberOfMembers++;
		
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
	
	public void deleteMember(String memberID){
		
		for(int i = 0; i < memberStorage.size(); i++){
			
			if(memberStorage.get(i).getMemberID().equals(memberID)){
				
				Member deletedMember = memberStorage.get(i);
				
				totalNumberOfMembers--;
				
				for(int j = 0; j < deletedMember.getBoatList().size(); j++){
					
					if(deletedMember.getBoatList().get(j).type.equals("Kayak")){
						
						totalNumberOfKayaks--;
						
					}
					else if(deletedMember.getBoatList().get(j).type.equals("MotorSailor")){
											
						totalNumberOfMotorSailor--;
											
					}
					else if(deletedMember.getBoatList().get(j).type.equals("SailBoat")){
						
						totalNumberOfSailBoats--;
						
					}
					else if(deletedMember.getBoatList().get(j).type.equals("Other")){
						
						totalNumberOfOtherBoats--;
						
					}
					
				}
				
				memberStorage.remove(i);
			}
			
		}
		
		String path = "src/resources/" + memberID +  ".txt";
		
		File file = new File(path);
		
		if(file.exists()){
			
			file.delete();
			
		}
		
		generalUpdate();
		
	}
	
	public void changeBoat(String memberID, String boatID, String length) {

		for (int i = 0; i < memberStorage.size(); i++) {

			if (memberStorage.get(i).getMemberID().equals(memberID)) {

				Member selectedMember = memberStorage.get(i);

				for (int j = 0; j < selectedMember.getBoatList().size(); j++){
					
					if (selectedMember.getBoatList().get(j).ID.equals(boatID)){
						
						selectedMember.getBoatList().get(j).length = length;
						
					}
					
				}
				
			}
			try {

				String path = "src/resources/" + memberID + ".txt";

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

	public void deleteBoat(String memberID, String boatID) {

		String updateBoats = null;
		
		for (int i = 0; i < memberStorage.size(); i++) {

			if (memberStorage.get(i).getMemberID().equals(memberID)) {

				Member selectedMember = memberStorage.get(i);

				for (int j = 0; j < selectedMember.getBoatList().size(); j++) {

					if (selectedMember.getBoatList().get(j).ID.equals(boatID)) {

						if (selectedMember.getBoatList().get(j).type.equals("Kayak")) {

							totalNumberOfKayaks--;

						} else if (selectedMember.getBoatList().get(j).type.equals("MotorSailor")) {

							totalNumberOfMotorSailor--;

						} else if (selectedMember.getBoatList().get(j).type.equals("SailBoat")) {

							totalNumberOfSailBoats--;

						} else if (selectedMember.getBoatList().get(j).type.equals("Other")) {

							totalNumberOfOtherBoats--;

						}
						memberStorage.get(i).setNumberOfBoats(Integer.toString(Integer.parseInt(memberStorage.get(i).getNumberOfBoats())-1));
						updateBoats = "numberOfBoats:" + memberStorage.get(i).getNumberOfBoats();
						memberStorage.get(i).getBoatList().remove(j);
						break;
					}
					
					updateBoats = "numberOfBoats:" + memberStorage.get(i).getNumberOfBoats();
					
				}
				
				try {

					String path = "src/resources/" + memberID + ".txt";

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

