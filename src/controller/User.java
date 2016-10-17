package controller;

import java.io.IOException;
import java.util.Scanner;

import model.Kayak;
import model.MotorSailor;
import model.Other;
import model.SailBoat;

public class User {

	private Scanner userInput;
	private int choice;
	private String answer;

	public boolean memberDisplay(view.Interface v_view, model.Admin a) throws IOException {
		while (v_view.exit() != true) {

			v_view.presentInstruction();
			v_view.collectEvents(getInput());

			if (v_view.addMember()) {
				String newMemberName = input();
				v_view.ID();
				String newMemberPersonalNumber = input();
				a.addMember(newMemberName, newMemberPersonalNumber);

				v_view.succesful(a);

			} else if (v_view.addBoat()) {
				String boatRegisterID = input();
				v_view.addBoatType();
				getInput();
				v_view.boatLength();
				String boatRegisterLength = input();

				if (choice == 1) {

					a.addBoat(boatRegisterID, new Kayak(null, null), boatRegisterLength);

				} else if (choice == 2) {

					a.addBoat(boatRegisterID, new SailBoat(null, null), boatRegisterLength);

				} else if (choice == 3) {

					a.addBoat(boatRegisterID, new MotorSailor(null, null), boatRegisterLength);

				} else {

					a.addBoat(boatRegisterID, new Other(null, null), boatRegisterLength);
				}
				
				v_view.succesful(a);
				
			} else if (v_view.deleteMember()) {
				String deleteMemberID = input();
				a.deleteMember(deleteMemberID);
				
				v_view.succesful(a);

			} else if (v_view.deleteMemberBoat()) {
				String deleteBoatMemberID = input();
				v_view.ID();
				String deleteBoatID = input();

				a.deleteBoat(deleteBoatMemberID, deleteBoatID);
				v_view.succesful(a);

			} else if (v_view.changeBoatInfo()) {
				String changeBoatMemberID = input();
				v_view.ID();
				String changeBoatID = input();
				v_view.boatLength();
				String changeBoatNewLength = input();

				a.changeBoat(changeBoatMemberID, changeBoatID, changeBoatNewLength);
				v_view.succesful(a);

			} else if (v_view.printCompactList(a)) {
				continue;

			} else if (v_view.printVerboseList(a)) {
				continue;

			} else {
				v_view.invalidNum();
			}
		}
		close();
		return true;
	}

	public void close() {
		userInput.close();
	}

	public String input() {
		userInput = new Scanner(System.in);
		
		if(userInput.hasNext()){
		
			answer = userInput.nextLine();
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
