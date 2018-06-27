package menu;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import database.DataBase;
import extra.TypeOfUser;

public class Main{   
	public static void main(String[] args) 
	{   
		String again="start";
		String status="out";


		DataBase db=DataBase.getConnect();
		db.createDataBase();
		do {
			Login log=new Login();
			status="in";
			TypeOfUser.setTypeOfUser(log.getTypeOfUser());
			do {
				Menu menu=new Menu(log.getTypeOfUser(),log.getUsername());

				System.out.println("do you want to start menu or to exit? (start/exit)");
				Scanner sc=new Scanner(System.in);
				again=sc.nextLine();
			} while (again.equals("start"));

			System.out.println("do you want to logout? (in/out)");
			Scanner sc=new Scanner(System.in);
			status=sc.nextLine();
		}while(status.equals("in"));
			db.copyToFile();
	}
}