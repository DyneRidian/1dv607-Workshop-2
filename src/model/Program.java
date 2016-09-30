package model;

public class Program {

	public static void main(String[] args) {

		Admin a = new Admin();
		a.populate();
		System.out.println(a.verboseList());

		/* copy text file so you can replace it after using this method
		 */
		a.deleteMember("1");	
		
		System.out.println(a.verboseList());
		
	}
}
