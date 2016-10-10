package view;

import java.io.IOException;

// Couldn't call the package interface so had to add an 's' to the end >.<

public class Interface {

	private int choice;

	public Interface(model.Admin a) {
		a.populate();
	}

	public void presentInstruction() throws IOException {

		System.out.println("-----------------------------------------------------");
		System.out.println("Enter number corresponding to action you wish to take");
		System.out.println("-----------------------------------------------------");
		System.out.println("1: Add new member");
		System.out.println("2: Register a new boat to a member");
		System.out.println("3: Delete a member");
		System.out.println("4: Delete a specific member's boat");
		System.out.println("5: Change a boat's information (length)");
		System.out.println("6: Print a compact list of all members");
		System.out.println("7: Print a verbose list of all members");
		System.out.println("8: Exit program");
		System.out.println("-----------------------------------------------------");

	}

	public void collectEvents(int input) {
		choice = input;
	}

	public boolean addMember() throws IOException {

		if (choice == 1) {

			System.out.println("Please enter a name for the member: ");
			System.out.println("Please enter the member's Personal Number: ");

			return true;

		}
		return false;
	}

	public void succesful(model.Admin a) {
		
		if(choice == 1){
		
			System.out.println("New member added! The ID for this member is: "
					+ a.getMemberStorage().get(a.getMemberStorage().size() - 1).getMemberID());
			
		}else if(choice == 2){
			
			System.out.println("Registered!");
			
		}else if(choice == 3){
			
			System.out.println("Member has been deleted");
			
		}else if(choice == 4){
			
			System.out.println("Boat deleted!");
			
		}else if(choice == 5){
			
			System.out.println("Boat Changed!");
			
		}
	}

	public boolean addBoat() {

		if (choice == 2) {

			System.out.println("Please enter the ID of the member you wish to register a boat to: ");

			return true;
		}

		return false;
	}

	public void addBoatType() throws IOException {

		System.out.println("Enter a number that corresponds to the boat you wish to register: ");

		System.out.println("1: Kayak");
		System.out.println("2: Sail Boat");
		System.out.println("3: Motor Sailor");
		System.out.println("4: Other (not listed)");
	
		System.out.println("Please enter the length of the boat: ");
	}

	public boolean deleteMember() {

		if (choice == 3) {

			System.out.println("Please enter the ID of the member you wish to delete: ");

			return true;
		}

		return false;

	}

	public boolean deleteMemberBoat() {

		if (choice == 4) {
			System.out.println("Please enter the ID of the member whose boat you wish to delete: ");
			System.out.println("Please enter the ID of the boat you wish to delete: ");

			return true;

		}

		return false;

	}

	public boolean changeBoatInfo() {

		if (choice == 5) {

			System.out.println("Please enter the ID of the member whose boat you wish to change: ");
			System.out.println("Please enter the ID of the boat you wish to change: ");
			System.out.println("Please enter the new length for the boat: ");

			return true;
		}

		return false;

	}

	public boolean printCompactList(model.Admin a) {

		if (choice == 6) {

			System.out.println("\n" + a.compactList());
			return true;

		}

		return false;
	}

	public boolean printVerboseList(model.Admin a) {

		if (choice == 7) {

			System.out.println("\n" + a.verboseList());
			return true;
		}

		return false;
	}

	public boolean exit() {

		if (choice == 8) {

			return true;

		}

		return false;
	}

	public void invalidNum() {

		if (choice < 0 || choice > 8) {

			System.err.println("Invalid number, please try again.\n");

		}

	}
}
