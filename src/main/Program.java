package main;

import java.io.IOException;

import controller.User;
import model.Admin;

public class Program {

	public static void main(String[] args) throws IOException {
		
		User m = new User();
		Admin a = new Admin();
		view.Interface v = new view.Interface(a);
		m.memberDisplay(v, a);
		
	}

}
