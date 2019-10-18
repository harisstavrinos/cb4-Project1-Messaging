package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import files.FileWrite;

import java.util.Set;

public class DataBase {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "password";
	/////////////////////
	private int choice;
	private String username;
	private String password;
	private String privilege;

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet res = null,rs=null;

	////////// Make db Singleton!/////////////////
	
	   private static DataBase dbConnect;
	  
	   public static DataBase getConnect() { if(dbConnect==null) dbConnect=new
	   DataBase(); return dbConnect; }
	 
	/////////////////////////////////////////////
	/*
	 * private DataBase(String username, String password){ this(); //
	 * this.username=username; // this.choice=choice; System.out.println("test"); }
	 */

	//////////////////////////////////////
	///////// CONSTRUCTOR /////////////
	/////////////////////////////////////

	private DataBase() {

		super();
	}

	//////////////////////////////////////
	///////// CREATE OF DATA BASE////////
	////////////////////////////////////

	public void createDataBase() {

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			System.out.println("Creating database...");
			stmt = conn.createStatement();
			// stmt.executeUpdate("DROP DATABASE BOOTCAM");
			////////////////////////////////////////////////////////
			// CREATING THE DATABASE ((((EX1))))//////////////////

			stmt.executeUpdate("DROP DATABASE IF EXISTS Project1");
			String sql = "CREATE DATABASE Project1";
			// + "CREATE TABLE STUDENTS";

			stmt.executeUpdate(sql);

			System.out.println("Database created successfully...");
			stmt.executeUpdate("USE Project1");

			stmt.executeUpdate("CREATE TABLE messages(MesID INT NOT NULL PRIMARY KEY,"
					+ "Sender VARCHAR(20), Receiver VARCHAR(20), MessageData VARCHAR(250)," +
					"DateTime DATETIME, NewMessage BOOLEAN)");
			stmt.executeUpdate("CREATE TABLE users(UsrID INT NOT NULL PRIMARY KEY,"
					+ "UserName VARCHAR(20), PassWord VARCHAR(20), Privilege VARCHAR(20))");

			// System.out.println("your choice:" + this.choice);

			// TABLE MESSAGES
			stmt.executeUpdate("INSERT INTO messages (MesID, Sender, Receiver, MessageData, DateTime, NewMessage)"
					+ " VALUES(1,'user','viewer','hello? how you doing?', NOW(), false) ");
			stmt.executeUpdate("INSERT INTO messages (MesID, Sender, Receiver, MessageData, DateTime, NewMessage)"
					+ " VALUES(2,'viewer','mod','all good. How about you?', NOW(), false) ");
			stmt.executeUpdate("INSERT INTO messages (MesID, Sender, Receiver, MessageData, DateTime,NewMessage)"
					+ " VALUES(3,'mod','super','I am fine', NOW(),false) ");
			stmt.executeUpdate("INSERT INTO messages (MesID, Sender, Receiver, MessageData, DateTime,NewMessage)"
					+ " VALUES(4,'super','admin','nice!', NOW(),false) ");
			stmt.executeUpdate("INSERT INTO messages (MesID, Sender, Receiver, MessageData, DateTime,NewMessage)"
					+ " VALUES(5,'admin','user','see ya!', NOW(),false) ");
			
			
			
			
			
			// TABLE USERS
			stmt.executeUpdate("INSERT INTO USERS (UsrID, UserName, Password, Privilege)"
					+ " VALUES(1,'admin','aDmI3$','superadmin') ");
			stmt.executeUpdate("INSERT INTO USERS (UsrID, UserName, Password, Privilege)"
					+ " VALUES(2,'mod','mod','moderator') ");
			stmt.executeUpdate("INSERT INTO USERS (UsrID, UserName, Password, Privilege)"
					+ " VALUES(3,'user','user','user') ");
			stmt.executeUpdate("INSERT INTO USERS (UsrID, UserName, Password, Privilege)"
					+ " VALUES(4,'super','super','supermoderator') ");
			stmt.executeUpdate("INSERT INTO USERS (UsrID, UserName, Password, Privilege)"
					+ " VALUES(5,'viewer','viewer','viewer') ");



			///// Map for username and password
			// HashMap hm = new HashMap();
			/*
			 * Map<String, String> map=new HashMap<String,String>();
			 * 
			 * //Iterator it= map.entrySet().iterator(); res.beforeFirst();
			 * while(res.next()) //
			 * map.put(res.getString('UserName'),res.getString('Password'));
			 * map.put("UserName", "1"); map.put("poutses", "2");
			 * System.out.println("using entrySet and toString"); for (Entry<String, String>
			 * entry : map.entrySet()) { System.out.println(entry); }
			 */

			//////////////////////////////////////////////////////////////////////
			//////////////////////// END OF TRY/////////////////////////////////////
			/////////////////////// CATCH-FINALY////////////////////////////////////
			////////////////////////////////////////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

		System.out.println("Data Base Created!");

	}

	////////////////////////////////////////////////////////////
	/////////////////// CHECK USER NAME//////////////////////////
	////////////////////////////////////////////////////////////
	public Boolean searchUserName(String username) {

		this.username = username;
		Boolean usrFound = false;

		/////////////////////// TRY ////////////////////////

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");
			res = stmt.executeQuery(
					"SELECT UserName,Password, Privilege FROM users WHERE UserName='" + this.username + "'");
			// while()
			while (res.next())
				if (this.username.equals(res.getString("UserName"))) {
					usrFound = true;
					this.password = res.getString("PassWord");
					setPrivilege(res.getString("Privilege"));
				}
			// System.out.println(usrFound);
			// return found;

			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
			return usrFound;
		} // end try
		///////////////////// END OF TRY////////////////////////////

	}
	///////////////// END OF CHECK USERNAME//////////////////////

	///////////////////////////////////////////
	//////////////// PASSWORD CHECK/////////////
	/////////////////////////////////////////

	public Boolean checkPassWord(String password) {
		Boolean passFound = false;
		if (this.password.equals(password)) {
			passFound = true;
		}
		return passFound;
	}

	//////////////////////////////////////////////////
	//////////// CREATE USER ////////////////////////
	///////////////////////////////////////////////
	public void createUser(String username, String password, String privilege) {

		int newUserId = 0;
		/////////////////////// TRY ////////////////////////

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");
			res = stmt.executeQuery("SELECT UsrID FROM users");
			res.last();
			newUserId =(int) res.getInt("UsrID");
			newUserId++;

			stmt.executeUpdate("INSERT INTO USERS (UsrID,Username,PassWord,Privilege) VALUES(" 
								+ newUserId +",'"+username+"','"+password+"','"+privilege+"')");

			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		} // end try
			///////////////////// END OF TRY////////////////////////////

	}
	
	
	//////////////////////////////////////////////////
	//////////// DELETE USER ////////////////////////
	///////////////////////////////////////////////
	public void deleteUser(String username) {

		int delUserId = 0;
		/////////////////////// TRY ////////////////////////

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");
		
			stmt.executeUpdate("DELETE FROM USERS"  
					+ "WHERE username='"+username+"'");

			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		} // end try
			///////////////////// END OF TRY////////////////////////////

	}
	
	
	//////////////////////////////////////////////////
	//////////// EDIT USER ////////////////////////
	///////////////////////////////////////////////
	public void editUser(String username, String privilege) {

		int newUserId = 0;
		/////////////////////// TRY ////////////////////////

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");
			
			stmt.executeUpdate("UPDATE USERS SET Privilege='"+privilege+"'"
					+ "WHERE username='"+username+"'");

			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		} // end try
			///////////////////// END OF TRY////////////////////////////

	}

	/////////////////////////////////////////////////
	//////////// DELETE  MESSAGE ///////////////////
	///////////////////////////////////////////////
	public void deleteMessage(int MesID) {

		
		/////////////////////// TRY ////////////////////////

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");
			
			stmt.executeUpdate("DELETE FROM MESSAGES WHERE MesID='"+MesID+"'");

			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		} // end try
			///////////////////// END OF TRY////////////////////////////

	}
	
	/////////////////////////////////////////////////
	//////////// EDIT  MESSAGE ///////////////////
	///////////////////////////////////////////////
	public String edtMessage(int MesID) {

		String message=null;
		/////////////////////// TRY ////////////////////////

		try {
			
			
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");
			
			res=stmt.executeQuery("SELECT MessageData FROM MESSAGES WHERE MesID='"+MesID+"'");
			if(res.next())
				message=res.getString("MessageData");
			System.out.println(res.getString("MessageData")); 
			
			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
			return message;
		} // end try
			///////////////////// END OF TRY////////////////////////////
	}	
	
	public void edtMessage2(int MesID, String message) {

		
		/////////////////////// TRY ////////////////////////

		try {
			
			
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");
			
			stmt.executeUpdate("UPDATE MESSAGES SET MessageData='"+ message + "' WHERE MesID='"+ MesID+"'");
			
			
			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
			
		} // end try
			///////////////////// END OF TRY////////////////////////////
	}	
	
/////////////////////////////////////////////////
//////////// DELETE  MESSAGE ///////////////////
///////////////////////////////////////////////
	public void viewAllMessages() {


		/////////////////////// TRY ////////////////////////

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");

			res=stmt.executeQuery("SELECT * FROM MESSAGES");
			
			while(res.next())
				System.out.println(res.getString("Sender") + " ,  " + res.getString("Receiver") + " ,  " + res.getString("MessageData") + " ,  " + res.getString("DateTime"));

			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		} // end try
		///////////////////// END OF TRY////////////////////////////

	}
	/////////////////////////////////////////////////
	////////////VIEW ALL  MESSAGES ///////////////////
	///////////////////////////////////////////////
	public void viewMyMessages() {


		/////////////////////// TRY ////////////////////////

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");

			res=stmt.executeQuery("SELECT * FROM MESSAGES");

			while(res.next())
				System.out.println(res.getString("Sender") + " ,  " + res.getString("Receiver") + " ,  " + res.getString("MessageData") + " ,  " + res.getString("DateTime"));

			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		} // end try
		///////////////////// END OF TRY////////////////////////////
	}
	
	
	/////////////////////////////////////////////////
	////////////SEND  MESSAGE ///////////////////
	///////////////////////////////////////////////
	public void sendMessage(String sender, String receiver, String message) {


		/////////////////////// TRY ////////////////////////

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");

			res=stmt.executeQuery("SELECT MesID FROM MESSAGES");
			res.last();
			int messageId=res.getInt("MesID")+1;
			stmt.executeUpdate("INSERT INTO MESSAGES (MesID, Sender, Receiver, MessageData, DateTime, NewMessage) VALUES "
					+ "('"+messageId+"','"+sender+"','"+receiver+"','"+ message +"', NOW(), True)");
			

			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		} // end try
		///////////////////// END OF TRY////////////////////////////
	}
	
/////////////////////////////////////////////////
////////////VIEW My  MESSAGES ///////////////////
///////////////////////////////////////////////
	public void viewMyMessages(String username) {


		/////////////////////// TRY ////////////////////////

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");

			res=stmt.executeQuery("SELECT * FROM MESSAGES WHERE sender='"+username+"' OR receiver='"+ username+"'");

			while(res.next())
				System.out.println(res.getString("Sender") + " ,  " + res.getString("Receiver") + " ,  " + res.getString("MessageData") + " ,  " + res.getString("DateTime"));

			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		} // end try
		///////////////////// END OF TRY////////////////////////////
	}

	/////////////////////////////////////////////////
	////////////VIEW My NEW MESSAGES ///////////////////
	///////////////////////////////////////////////
	public void viewNewMessages(String username) {


		/////////////////////// TRY ////////////////////////

		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");

			res=stmt.executeQuery("SELECT * FROM MESSAGES WHERE sender='"+username+"' OR receiver='"+ username+"'");

			while(res.next())
				if(res.getBoolean("NewMessage"))
					System.out.println(res.getString("Sender") + " ,  " + res.getString("Receiver") + " ,  " + res.getString("MessageData") + " ,  " + res.getString("DateTime"));
			stmt.executeUpdate("UPDATE  MESSAGES SET NewMessage=false WHERE sender='"+username+"' OR receiver='"+ username+"'");
			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		} // end try
		///////////////////// END OF TRY////////////////////////////
	}
	
	/////////////////////////////////////////////////
	////////////COPY MESSAGES TO FILE  //////////////
	///////////////////////////////////////////////
	@SuppressWarnings("null")
	public void copyToFile() {


		/////////////////////// TRY ////////////////////////

		try {
			 
			
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			stmt.executeQuery("USE Project1");

			int i=0;
			int max=0;
			res=stmt.executeQuery("SELECT * FROM MESSAGES");
			rs=res;
			if(rs.last())
				 max=rs.getRow();
				 max++;	
			System.out.println("malines: " + max);
			String[] messages=new String[max];
			messages[0]= "MesID,  Sender , Receiver  ,  MessageData ,  DateTime , NewMessage " ;
			res=stmt.executeQuery("SELECT * FROM MESSAGES");
			while(res.next()) {
				messages[++i]=res.getString("MesID") + " ,  " + res.getString("Sender") + " ,  " + res.getString("Receiver") + " ,  " + res.getString("MessageData") + " ,  " + res.getString("DateTime") + " , "
				+ res.getString("NewMessage");
			}
			
			 for(int j=0; j<max;j++) {
				 System.out.println(messages[j]);
			 }
			new FileWrite(messages, max);
			
			
			/////////////////////// CATCH-FINALY////////////////////////
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try

		} // end try
		///////////////////// END OF TRY////////////////////////////
	}
	
	//////////////////////////////////////////////////////////////
	////////////// GETTERS & SETTERS//////////////////////////////
	////////////////////////////////////////////////////////////

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

}
