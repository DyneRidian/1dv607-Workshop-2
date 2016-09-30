package model;

import java.io.IOException;

public class Program {

	public static void main(String[] args) throws IOException {

		Admin a = new Admin();
		a.populate();
		System.out.println(a.verboseList());

		/* copy text file so you can replace it after using this method
		 */
		
		a.addMember("Testicle", "920205-4970");
		a.addBoat("3",new Kayak(null, null), "5");
		
		System.out.println(a.verboseList());
		
	}
}
