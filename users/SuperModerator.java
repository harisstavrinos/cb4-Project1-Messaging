package users;
import java.util.Scanner;

import extra.TypeOfUser;
import menu.Choices;

public class SuperModerator extends Moderator {

	
	
	public SuperModerator(String username) {
		super(username);
		System.out.println("6. Delete message ");
		if(TypeOfUser.getTypeOfUser().equals("supermoderator")){
			System.out.println(getClass());
			System.out.println("Select one of the above choises:");
			Scanner sc=new Scanner(System.in); 
			this.setChoice(sc.nextInt());
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
			}
		}
		
	}
	

}
