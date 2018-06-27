package users;


  
import menu.*;
import database.*;
import extra.*;

import java.util.Scanner;

public class User {
	public int choice;
	public String username;
   
	
	public User(String username) {
		super();
		this.username=username;
		Choices c=new Choices();

		System.out.println("1. Send a message");
		System.out.println("2. View my messages ");
		System.out.println("3. View new messages");

		if(TypeOfUser.getTypeOfUser().equals("user")){
			System.out.println(getClass());
			System.out.println("Select one of the above choises:");
			Scanner sc=new Scanner(System.in); 
			this.setChoice(sc.nextInt());
		
		
		DataBase db=DataBase.getConnect(); 
		switch (choice){
		case 1:
			c.choice1(username);
			break;
		case 2:
			c.choice2(username);		
			break;
		case 3:
			c.choice3(username);
			break;
		}
	}
	}//end of constructor
	

	/*
	
	public String sendMessage()[] {
		String sendMsg[]=new String[2];
		Scanner sc=new Scanner(System.in);

		System.out.println("Who will be the receiver?:");
		sendMsg[0]=sc.nextLine();
		System.out.println("Write your message:");
		sendMsg[1]=sc.nextLine();
		return sendMsg;
	}
	*/
	
	
	///////////SETTERS & GETERS/////
	
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
		
}
