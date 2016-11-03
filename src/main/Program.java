package main;

import java.io.IOException;
import controller.User;

public class Program {

	public static void main(String[] args) throws IOException {
		
		User m = new User();
		model.Admin a = new model.Admin();
		view.Interface v = new view.Interface(a);
		
		m.memberDisplay(v, a);
		
	}

}
