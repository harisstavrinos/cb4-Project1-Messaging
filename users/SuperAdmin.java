package users;
import java.util.Scanner;

import database.DataBase;
import menu.Choices;

public class SuperAdmin extends SuperModerator {

	public SuperAdmin(String username) {
		super(username);
		DataBase db=DataBase.getConnect();
		int choice=getChoice();
		System.out.println("7. Create user");
		System.out.println("8. Delete user");
		System.out.println("9. Change privileges");
		Scanner sc=new Scanner(System.in);
		super.setChoice(sc.nextInt());
		
		Choices c=new Choices();
		switch (super.choice){
		case 1:
			c.choice1(username);
			break;
		case 2:
			c.choice2(username);		
			break;
		case 3:
			c.choice3(username);
			break;
		case 4:
			c.choice4(username);
			break;		
		case 5:
			c.choice5(username);
			break;	
		case 6:
			c.choice6(username);
			break;	
		case 7:
			c.choice4(username);
			break;		
		case 8:
			c.choice5(username);
			break;	
		case 9:
			c.choice6(username);
			break;	
		}
	}
	
	
	

}
