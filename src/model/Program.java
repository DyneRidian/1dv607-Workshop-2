package model;

public class Program {

	public static void main(String[] args) {

		Boat b = new Kayak(10);
		Boat c = new Kayak(120);
		Member m = new Member("Christofer", "920804");

		m.addBoat(b);
		m.addBoat(c);
		
		Admin a = new Admin(m);

		System.out.println(a.verboseList());

	}
}
