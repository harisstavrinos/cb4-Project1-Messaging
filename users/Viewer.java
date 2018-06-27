package users;
import java.util.Scanner;

import extra.TypeOfUser;
import menu.Choices;


public class Viewer extends User{

	public Viewer(String username) {
		super(username);
		Choices c=new Choices();
		System.out.println("4. View all messages ");
		if(TypeOfUser.getTypeOfUser().equals("viewer")){
			System.out.println(getClass());
			System.out.println("Select one of the above choises:");
			Scanner sc=new Scanner(System.in); 
			this.setChoice(sc.nextInt());
		
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
		case 4:
			c.choice4(username);
			break;		
		}
	 }
   }
		/*DataBase db=DataBase.getConnect();
		if (super.choice==4) {
			db.viewAllMessages();
		}
	}*/
	

}

