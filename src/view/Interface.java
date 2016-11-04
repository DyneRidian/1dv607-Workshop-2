package view;

import java.io.IOException;

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

	public void succesful(model.Admin a) {

		if (choice == 1) {

			System.out.println("New member added! The ID for this member is: "
					+ a.getMemberStorage().get(a.getMemberStorage().size() - 1).getMemberID());

		} else if (choice == 2) {

			System.out.println("\n Registered!");

		} else if (choice == 3) {

			System.out.println("\n Member has been deleted");

		} else if (choice == 4) {

			System.out.println("\n Boat deleted!");

		} else if (choice == 5) {

			System.out.println("\n Boat Changed!");

		}
	}

	public void errorFormat(model.Admin a) {

		if (choice == 1) {

			System.out.println();
			System.out.println("\n *The new member could not be added.*");
			System.out.println("*Only NUMBERS are allowed in personal numbers!*");

		} else if (choice == 2) {

			System.out.println();
			System.out.println("\n *The new boat could not be registered.*");
			System.out.println("*Only NUMBERS are allowed in boat length!*");

		} else if (choice == 5) {

			System.out.println();
			System.out.println("\n *The boat data could not be changed*");
			System.out.println("*Only NUMBERS are allowed in boat length!*");

		}
	}

	// errors for Member id
	public void errorMemberIdNotFound(model.Admin a) {

		if (choice == 2) {

			System.out.println();
			System.out.println("\n *The new boat could not be registered.*");
			System.out.println("*Could not find any members with that ID!*");
		}

		else if (choice == 3) {

			System.out.println();
			System.out.println("\n *The member could not be deleted.*");
			System.out.println("*Could not find any member with that ID!*");

		} else if (choice == 4) {

			System.out.println();
			System.out.println("\n *The boat could not be deleted.*");
			System.out.println("*Could not find any member with that ID!*");

		}

		else if (choice == 5) {
			System.out.println();
			System.out.println("\n *The boat data could not be changed.*");
			System.out.println("*Could not find any member with that ID!*");

		}
	}

	// errors for Boat id
	public void errorBoatIdNotFound(model.Admin a) {

		if (choice == 4) {

			System.out.println();
			System.out.println("\n *The boat could not be deleted.*");
			System.out.println("*Could not find any boat with that ID!*");

		} else if (choice == 5) {

			System.out.println();
			System.out.println("\n *The boat data could not be changed*");
			System.out.println("*Could not find any boat with that ID!*");

		}

	}

	public boolean addMember() throws IOException {

		if (choice == 1) {

			System.out.println("Please enter a name for the member: ");

			return true;

		}

		return false;
	}

	public boolean addBoat() {

		if (choice == 2) {

			System.out.println("Please enter the ID of the member you wish to register a boat to: ");

			return true;
		}

		return false;
	}

	public void addBoatType() {

		System.out.println("Enter a number that corresponds to the boat you wish to register: ");

		System.out.println("1: Kayak");
		System.out.println("2: Sail Boat");
		System.out.println("3: Motor Sailor");
		System.out.println("4: Other (not listed)");

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

			return true;

		}

		return false;

	}

	public boolean changeBoatInfo() {

		if (choice == 5) {

			System.out.println("Please enter the ID of the member whose boat you wish to change: ");

			return true;
		}

		return false;

	}

	private String compactList(model.Admin a) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.getMemberStorage().size(); i++) {

			sb.append("Name: " + a.getMemberStorage().get(i).getName() + "\n");
			sb.append("Member ID: " + a.getMemberStorage().get(i).getMemberID() + "\n");
			sb.append("Number of Boats: " + a.getMemberStorage().get(i).getNumberOfBoats() + "\n");
			sb.append("-------------------------------------------- \n");

		}

		return sb.toString();
	}

	public boolean printCompactList(model.Admin a) {

		if (choice == 6) {

			System.out.println("\n" + compactList(a));
			return true;

		}

		return false;
	}

	private String verboseList(model.Admin a) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.getMemberStorage().size(); i++) {

			sb.append("Name: " + a.getMemberStorage().get(i).getName() + "\n");
			sb.append("Personal Number: " + a.getMemberStorage().get(i).getPersonalNumber() + "\n");
			sb.append("Member ID: " + a.getMemberStorage().get(i).getMemberID() + "\n");
			for (int j = 0; j < a.getMemberStorage().get(i).getBoatList().size(); j++) {

				sb.append("Boat Type: " + a.getMemberStorage().get(i).getBoatList().get(j).getType() + "\n");
				sb.append("Boat Length: " + a.getMemberStorage().get(i).getBoatList().get(j).getLength() + "\n");
			}
			sb.append("-------------------------------------------- \n");
		}
		return sb.toString();
	}

	public boolean printVerboseList(model.Admin a) {

		if (choice == 7) {

			System.out.println("\n" + verboseList(a));
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

	public void boatLength() {

		if (choice == 2 || choice == 5) {

			System.out.println("Please enter the length of the boat in meters: ");

		}
	}

	public void ID() {

		if (choice == 1) {

			System.out.println("Please enter the member's Personal Number: ");

		} else if (choice == 4) {

			System.out.println("Please enter the ID of the boat you wish to delete: ");

		} else if (choice == 5) {

			System.out.println("Please enter the ID of the boat you wish to change: ");

		}

	}

}
