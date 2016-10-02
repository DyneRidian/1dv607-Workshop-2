package model;

import java.io.IOException;

public class Program {

	public static void main(String[] args) throws IOException {

		Admin a = new Admin();
		a.populate();
		
		a.changeBoat("1", "1", "120");
		
		//a.addBoat("1", new Kayak(null,null), "5");
		
		//a.deleteBoat("1","1");
		
		//System.out.println(a.getMemberStorage().get(0).getBoatList().get(0).boatType());
		//System.out.println(a.getMemberStorage().get(0).getBoatList().get(1).boatType());
		
	}
}
