package menu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.DataBase;

import java.util.*;

import java.util.Scanner;
import javax.swing.JTextField;

public class Login{ 
	
	public String username;
	public String password;
	public String typeOfUser;
	
/////////////////////////////////
	/*private static Login log;
	
	public static Login getLog() {
	if(log==null) 
		log=new Login();
		return log;
	}*/
	///////////////////
	public Login() { 	
	
		Boolean userFound=false;
		Boolean passFound=false;
		DataBase db=DataBase.getConnect();
		
		while(!userFound) {	
			this.usrnm();
			userFound=db.searchUserName(this.getUsername());
			if(userFound) 
					break;
			System.out.println("There is no such username. Try again");
		} 
		
		while(!passFound) {	
			this.psw();
			passFound=db.checkPassWord(this.getPassword());
			if(passFound) 
					break;
			System.out.println("There is no such username. Try again");
		} 
		System.out.println("Well Done!!!");
		System.out.println("You are: " + db.getPrivilege());
		setTypeOfUser(db.getPrivilege());
	 //if username.equals("admin");
		
	}
		
	
	

	public void psw() {
		final String password,message = "Enter password";
		if( System.console() == null ) 
		{ // inside IDE like Eclipse or NetBeans
		//  final JTextField    jtf = new JTextField("User name",10);
		  final JPasswordField pf = new JPasswordField(); 
		  
		/* usernam = JOptionPane.showConfirmDialog( null, jtf, "User Name",
		  		    JOptionPane.OK_CANCEL_OPTION,
		  		    JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION ? 
		  		      new String( jtf.getText() ) : "";*/
		  		      
		  password = JOptionPane.showConfirmDialog( null, pf, message,
		    JOptionPane.OK_CANCEL_OPTION,
		    JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION ? 
		      new String( pf.getPassword() ) : "";
		  this.password=password;	    
		}
		else 
		  password = new String( System.console().readPassword( "%s> ", message ) );
	}
	
	
	public void usrnm() {
		final String usernam, message = "Enter password";
		if( System.console() == null ) 
		{ // inside IDE like Eclipse or NetBeans
		  final JTextField    jtf = new JTextField("",10);
	
		  
		 usernam = JOptionPane.showConfirmDialog( null, jtf, "User Name",
		  		    JOptionPane.OK_CANCEL_OPTION,
		  		    JOptionPane.QUESTION_MESSAGE ) == JOptionPane.OK_OPTION ? 
		  		      new String( jtf.getText() ) : "";
		 System.out.println(); 		  
		 this.username=usernam; 
		 System.out.println(username);
		}
		else 
		  usernam = new String( System.console().readPassword( "%s> ", message ) );
	}
	
	////// Getters & Setters 
	
	public String getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}



    public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
		
}	
