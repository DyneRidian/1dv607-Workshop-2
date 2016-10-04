package controller;

import java.io.IOException;

import view.Interface;

public class User {

	public boolean memberDisplay() throws IOException{
		Interface v = new Interface();
		
		v.presentInstruction();
		
		return true;
	}	
}
