package menu;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import database.DataBase;
import users.Moderator;
import users.SuperAdmin;
import users.SuperModerator;
import users.User;
import users.Viewer;

public class Menu {
	private String typeOfUser;	
	private int choice;
	
	
	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public Menu(String typeOfUser,String username) {
		super();
		this.typeOfUser=typeOfUser;
		Scanner sc=new Scanner(System.in);
		DataBase db=DataBase.getConnect();
		
		
		
		//	userMenu();
			switch(typeOfUser) { 
			case "superadmin":
				new SuperAdmin(username);
				break;
			case "supermoderator":
				new SuperModerator(username);			
				break;
			case "moderator":
				new Moderator(username);
				break;
			case "viewer":
				new Viewer(username);
				break;
			case "user":
				new User(username);
				break;
		
			default:
				System.out.println("error!");
		}
			
			
			
			
			/*
		System.out.println("Select one of the above choises:");
		this.setChoice(sc.nextInt());
		
		//System.out.println(newU[0] + newU[1] + newU[2]);
		int choice=getChoice();
		if (choice==1) {
			String newU[]=createUser();
			db.createUser(newU[0],newU[1],newU[2]);
		}
		if (choice==2) {
			//String usr,pass;
			String edtU[]=editUser();
			db.editUser(edtU[0], edtU[1]);
		}
		
		if (choice==3) {
			//String usr,pass;
			String edtU[]=editUser();
			db.editUser(edtU[0], edtU[1]);
		}
		
		if (choice==4) {
			//String usr,pass;
			int delMes=deleteMessage();
			db.deleteMessage(delMes);
		}
		if (choice==5) {
			int edtMes=edtMessage();
			String txtMessage=db.edtMessage(edtMes);
			EditText.text=txtMessage; 
          /////////////// JTextArea///////////////////////////////////
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                TextAreaGetContent.showFrame();
	            }
	        });
         ///////////////////////////////////////////////////						
		}//end of edit Messages
		
		if (choice==6) {
			db.viewAllMessages();
		}
		if (choice==7) {
			String sndMsg[]=sendMessage();
			String sender=username;
			String receiver=sndMsg[0];
			String message=sndMsg[1];
			db.sendMessage(sender,receiver, message);			
		}
		if (choice==8) {
			db.viewMyMessages(username);			
		}
		
		*/
	}

	
///////////////Methods of choices////////////////	
	
	public String createUser()[] {
		String newUser[]=new String[3];
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Give Username:");
		newUser[0]=sc.nextLine();
		System.out.println("Give Password:");
		newUser[1]=sc.nextLine();
		System.out.println("Give Privilege:");
		newUser[2]=sc.nextLine();
		return newUser;
	}
	public String delUser() {
		String delUser;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Give Username:");
		delUser=sc.nextLine();
		
		return delUser;
	}
	
	public String editUser()[] {
		String editUser[]=new String[2];
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Give Username:");
		editUser[0]=sc.nextLine();
		System.out.println("Give Privilege:");
		editUser[1]=sc.nextLine();
		return editUser;
	}
	
	public int deleteMessage(){
		int delMes;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Give the ID of the message, which you want to delete:");
		delMes=sc.nextInt();
		
		return delMes;
	}
	
	public int edtMessage(){
		int edtMes;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Give the ID of the message, which you want to edit:");
		edtMes=sc.nextInt();
		
		return edtMes;
	}
	
	public String sendMessage()[] {
		String sendMsg[]=new String[2];
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Who will be the receiver?:");
		sendMsg[0]=sc.nextLine();
		System.out.println("Write your message:");
		sendMsg[1]=sc.nextLine();
		return sendMsg;
	}


}
