package users;
import java.sql.Time;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import extra.TypeOfUser;
import menu.Choices;

public class Moderator extends Viewer {

	public Moderator(String username) {
		super(username);
		System.out.println("5. Edit message ");
		if(TypeOfUser.getTypeOfUser().equals("moderator")){
			System.out.println(getClass());
			System.out.println("Select one of the above choises:");
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
				c.choice3(username);
				break;		
			}
		}
	}	
	
}
