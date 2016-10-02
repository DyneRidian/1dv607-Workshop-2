package interfaces;

import java.io.IOException;
import java.util.Scanner;

import model.Admin;
import model.Kayak;
import model.MotorSailor;
import model.Other;
import model.SailBoat;

// Couldn't call the package interface so had to add an 's' to the end >.<

public class Interface {

	public static void main(String[] args) throws IOException {
		
		Admin a = new Admin();
		a.populate();
		
		Scanner userInput = new Scanner(System.in);
		
		start:
			while(true){
				System.out.println("-----------------------------------------------------");
				System.out.println("Enter number corresponding to action you wish to take");
				System.out.println("-----------------------------------------------------");
				System.out.println("1: Add new member");
				System.out.println("2: Register a new boat to a member");
				System.out.println("3: Delete a member");
				System.out.println("4: Delete a specific member's boat");
				System.out.println("5: Print a compact list of all members");
				System.out.println("6: Print a verbose list of all members");
				System.out.println("7: Exit program");
				System.out.println("-----------------------------------------------------");
				
				int choice = userInput.nextInt();
				
				switch(choice){
				
					case 1: 
						System.out.print("Please enter a name for the member: ");
						userInput.nextLine();
						String newMemberName = userInput.nextLine();
						System.out.print("\nPlease enter the member's Personal Number: ");
						String newMemberPersonalNumber = userInput.next();
						a.addMember(newMemberName, newMemberPersonalNumber);
						System.out.println("\nNew member added! The ID for this member is: " +
								a.getMemberStorage().get(a.getMemberStorage().size()-1).getMemberID());
						break;
							
					case 2: 
						System.out.print("Please enter the ID of the member you wish to register a boat to: ");
						String boatRegisterID = userInput.next();
						System.out.println("\nEnter a number that corresponds to the boat you wish to register: ");
						System.out.println("1: Kayak");
						System.out.println("2: Sail Boat");
						System.out.println("3: Motor Sailor");
						System.out.println("4: Other (not listed)");
						choice = userInput.nextInt();
						System.out.print("\nPlease enter the length of the boat: ");
						String boatRegisterLength = userInput.next();
						switch(choice){
							case 1:
								a.addBoat(boatRegisterID, new Kayak(null, null), boatRegisterLength);
								break;
							case 2:
								a.addBoat(boatRegisterID, new SailBoat(null, null), boatRegisterLength);
								break;
							case 3:
								a.addBoat(boatRegisterID, new MotorSailor(null, null), boatRegisterLength);
								break;
							default:
								a.addBoat(boatRegisterID, new Other(null, null), boatRegisterLength);
								break;
						}
						System.out.println("\nRegistered!");
						break;
							
					case 3: 
						System.out.print("Please enter the ID of the member you wish to delete: ");
						String deleteMemberID = userInput.next();
						a.deleteMember(deleteMemberID);
						System.out.println("\nMember has been deleted");
						break;	
					
					case 4:
						System.out.print("Please enter the ID of the member whose boat you wish to delete: ");
						String deleteBoatMemberID = userInput.next();
						System.out.print("Please enter the ID of the boat you wish to delete: ");
						String deleteBoatID = userInput.next();
						a.deleteBoat(deleteBoatMemberID, deleteBoatID);
						break;
						
					case 5: 
						System.out.println("\n" + a.compactList());
						break;
							
					case 6: 
						System.out.println("\n" + a.verboseList());
						break;	
						
					case 7: 
						break start;
							
					default:
						System.err.println("Invaled number, please try again.\n");
					
				}

			}
		
		userInput.close();
		
	}

}
