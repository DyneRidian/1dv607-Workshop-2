package main;

import java.io.IOException;

import controller.User;

public class Program {

	public static void main(String[] args) throws IOException {
		
		User m = new User();
		view.Interface v = new view.Interface();
		
		m.memberDisplay(v);
		
	}

}
