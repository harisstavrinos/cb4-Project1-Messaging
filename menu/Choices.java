package menu;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;

import database.DataBase;
import extra.Flag;
import window.EditText;
import window.TextAreaGetContent;

public class Choices {
	DataBase db=DataBase.getConnect(); 

	public void choice1(String username) {
		String sndMsg[]=sendMessage();
		String sender=username;
		String receiver=sndMsg[0];
		String message=sndMsg[1];
		db.sendMessage(sender,receiver, message);			
	} 
	public void choice2(String username){
		db.viewMyMessages(username);			
	}
	public void choice3(String username){
		db.viewNewMessages(username);			
	}
	public void choice4(String username){
		db.viewAllMessages();			
	}
	public void choice5(String username) {
			
			/*System.out.println("Select one of the above choises:");
			Scanner sc=new Scanner(System.in);
			super.setChoice(sc.nextInt());*/
			int edtMes=this.edtMessage();
			DataBase db=DataBase.getConnect();
			String txtMessage=db.edtMessage(edtMes);
			EditText.text=txtMessage; 
			
			Flag.setFlag(false);
          /////////////// JTextArea///////////////////////////////////
			SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                TextAreaGetContent.showFrame();
	            }
	         
	        });
			  
			System.out.println(Flag.getFlag());
			
			System.out.println(Flag.getFlag());
		///////////////////////////////////////////////////////////
			 while(Flag.getFlag().equals(false)){
				 try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//waiting....;
	   			}
			db.edtMessage2(edtMes,EditText.getText());
			System.out.println("test");
		}
	
	public void choice6(String username) {

			//String usr,pass;
			DataBase db=DataBase.getConnect();
			int delMes=this.deleteMessage();
			db.deleteMessage(delMes);
		
	}
	
	public void choice7(String username) {
		String newU[]=createUser();
		db.createUser(newU[0],newU[1],newU[2]);
	}
	public void choice8(String username) {
		//String usr,pass;
		String edtU[]=editUser();
		db.editUser(edtU[0], edtU[1]);
	}
	
	public void choice9(String username) {
		//String usr,pass;
		String edtU[]=editUser();
		db.editUser(edtU[0], edtU[1]);
	}
	
	
	
	
	////////////////////
	

	public String sendMessage()[] {
		String sendMsg[]=new String[2];
		Scanner sc=new Scanner(System.in);

		System.out.println("Who will be the receiver?:");
		sendMsg[0]=sc.nextLine();
		System.out.println("Write your message:");
		sendMsg[1]=sc.nextLine();
		return sendMsg;
	}
	
	public int edtMessage(){
		int edtMes;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Give the ID of the message, which you want to edit:");
		edtMes=sc.nextInt();
		
		return edtMes;
	}
	public int deleteMessage(){
		int delMes;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Give the ID of the message, which you want to delete:");
		delMes=sc.nextInt();
		
		return delMes;
	}
	
	
	
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


}