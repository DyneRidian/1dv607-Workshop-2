package controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.Kayak;
import model.MotorSailor;
import model.Other;
import model.SailBoat;

public class User {

	private Scanner userInput;
	private int choice;

	public boolean memberDisplay(view.Interface v_view, model.Admin admin) throws IOException {
		while (v_view.exit() != true) {

			v_view.presentInstruction();
			v_view.collectEvents(getInput());

			if (v_view.addMember()) {
				String newMemberName = input();
				v_view.ID();
				String newMemberPersonalNumber = input();
				
				if(Pattern.matches("^[0-9]*$", newMemberPersonalNumber) == true && newMemberPersonalNumber.length() == 10 ){
					newMemberPersonalNumber  = newMemberPersonalNumber.substring(0,6) + "-" + newMemberPersonalNumber.substring(6,10);
					admin.addMember(newMemberName, newMemberPersonalNumber);
					v_view.succesful(admin);
				}
				else {
				v_view.errorFormat(admin);
				}
				
				

			} else if (v_view.addBoat()) {
				
				//loading inputs 
				String boatRegisterID = input();
				v_view.addBoatType();
				this.choice = getInput();
				v_view.boatLength();
				String boatRegisterLength = input();
				
				//checking if there is a member with the ID
				boolean IDFound = false;
				for(int i=0; i < admin.getMemberStorage().size();i++, System.out.println("asd") )
				{
					if(admin.getMemberStorage().get(i).getMemberID().equals(boatRegisterID)){
					IDFound = true;
					}
				}
				
				//checking for correct format
				if(Pattern.matches("^[0-9]*$", boatRegisterLength) == true && IDFound == true){
				boatRegisterLength = boatRegisterLength + "m";
				admin.helpAddBoat(boatRegisterID, choice, boatRegisterLength);
				v_view.succesful(admin);
				}
				
				//errors
				else if(IDFound == false){
					v_view.errorMemberIdNotFound(admin);
				}
				else {
					v_view.errorFormat(admin);
				}
				
			} else if (v_view.deleteMember()) {
				//loading inputs
				String deleteMemberID = input();
				
				//checking if there is a member with the ID
				boolean IDFound = false;
				for(int i=0;i < admin.getMemberStorage().size();i++ )
				{
					if(admin.getMemberStorage().get(i).getMemberID().equals(deleteMemberID)){
					IDFound = true;
					}
				}
				
				if(IDFound == true){
				admin.deleteMember(deleteMemberID);
				v_view.succesful(admin);
				}
				//errors
				else if(IDFound == false){
					v_view.errorMemberIdNotFound(admin);
				}
				

			} else if (v_view.deleteMemberBoat()) {
				//loading inputs
				String deleteBoatMemberID = input();
				v_view.ID();
				String deleteBoatID = input();
				
				//checking if there is a member and boat with the ID
				boolean IDFound = false;
				boolean BoatIDFound = false;
			
				for(int i=0; i<admin.getMemberStorage().size();i++ )
				{
					if(admin.getMemberStorage().get(i).getMemberID().equals(deleteBoatMemberID)){
					IDFound = true;
					for(int j=0;j < admin.getMemberStorage().get(i).getBoatList().size();j++ )
					{
						if(admin.getMemberStorage().get(i).getBoatList().get(j).getID().equals(deleteBoatID)){
						BoatIDFound = true;}
					}
					}
				}
				if(IDFound == true && BoatIDFound == true){
				admin.deleteBoat(deleteBoatMemberID, deleteBoatID);
				v_view.succesful(admin);
				}
				//errors
				else if (IDFound == false){
					v_view.errorMemberIdNotFound(admin);
				}
				else if (BoatIDFound == false){
					v_view.errorBoatIdNotFound(admin);
				}

			} else if (v_view.changeBoatInfo()) {
				//loading input
				String changeBoatMemberID = input();
				v_view.ID();
				String changeBoatID = input();
				v_view.boatLength();
				String changeBoatNewLength = input();
				
				//checking if there is a member and boat with the ID
				boolean IDFound = false;
				boolean BoatIDFound = false;
				for(int i=0;i < admin.getMemberStorage().size();i++ )
				{
					if(admin.getMemberStorage().get(i).getMemberID().equals(changeBoatMemberID)){
					IDFound = true;
					for(int j=0;j < admin.getMemberStorage().get(i).getBoatList().size();j++ )
					{
						if(admin.getMemberStorage().get(i).getBoatList().get(j).getID().equals(changeBoatID)){
						BoatIDFound = true;
						}
					}
					}
				}
				
				//checking for correct format
				if(Pattern.matches("^[0-9]*$", changeBoatNewLength) == true && IDFound == true && BoatIDFound == true){
				changeBoatNewLength = changeBoatNewLength + "m";
				admin.changeBoat(changeBoatMemberID, changeBoatID, changeBoatNewLength);
				v_view.succesful(admin);
				}
				//errors
				else if (IDFound == false){
					v_view.errorMemberIdNotFound(admin);
				}
				else if (BoatIDFound == false){
					v_view.errorBoatIdNotFound(admin);
				}
				else {
				v_view.errorFormat(admin);
				}
				

			} else if (v_view.printCompactList(admin)) {
				continue;

			} else if (v_view.printVerboseList(admin)) {
				continue;

			} else {
				v_view.invalidNum();
			}
		}
		close();
		return false;
	}

	public void close() {
		userInput.close();
	}

	public String input() {
		userInput = new Scanner(System.in);
		
		if(userInput.hasNextLine()){
		
			String answer = userInput.nextLine();
			return answer;
			
		}

		return "null";
	}

	public int getInput() {
		userInput = new Scanner(System.in);

		if (userInput.hasNextInt()) {
			choice = userInput.nextInt();
			return choice;
		}

		return -1;
	}
}
