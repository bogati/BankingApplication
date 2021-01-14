package com.jump.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



//https://www.tutorialspoint.com/h2_database/h2_database_jdbc_connection.htm 
public class ConnectionManager {

    
	private static final String URL = "jdbc:h2:mem:dollarbankdb";
	


	private static final String USERNAME = "root";
	private static final String PASSWORD = "Root@123"; 
														
// The goal is to have only one connection open during the session of entire application
	private static Connection conn; // will be null at moment
	private static Statement stmt = null;
	
	
	private static void makeConnection() {
		
		try {
			// for mysql : com.mysql.cj.jdbc.Driver 
			Class.forName("org.h2.Driver");
			System.out.println("Registered Driver from Singleton class");
			
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Connected  from Singleton class ");
			
			
			stmt = conn.createStatement();
	        System.out.println("Creating table in given database..."); 
	        
	        String sql = "create table User (\n"
	        		+ "userId int not null primary key auto_increment,\n"
	        		+ "firstName varchar(50),\n"
	        		+ "lastName varchar(50),\n"
	        		+ "age int,\n"
	        		+ "addrCountry varchar(30),\n"
	        		+ "addrState varchar(20),\n"
	        		+ "addrCity varchar(20),\n"
	        		+ "phoneNumber varchar(12),\n"
	        		+ "email varchar(50),\n"
	        		+"password varchar(30)\n"
	        		+ ");";
	        
	        stmt.executeUpdate(sql);
	        
	        System.out.println("Created table in given database...");
	        
	        sql = "INSERT INTO USER \n"
	        		+ "VALUES (null,'jai','sena',24,'USA','CA','San Jose','510-345-7090','bhkfle1@gmail.com','sanM@teo1');\n"
	        		+ "\n"
	        		+ "INSERT INTO USER \n"
	        		+ "VALUES (null,'hari','gupta',25,'USA','CA','San Jose','510-345-7091','bhkfle2@gmail.com','sanM@teo1');\n"
	        		+ "\n"
	        		+ "INSERT INTO USER \n"
	        		+ "VALUES (null,'victory','rawal',26,'USA','CA','San Jose','510-345-7092','bhkfle3@gmail.com','sanM@teo1');\n"
	        		+ "\n"
	        		+ "INSERT INTO USER \n"
	        		+ "VALUES (null,'bhai','adhikari',27,'USA','CA','San Jose','510-345-7093','bhkfle4@gmail.com','sanM@teo1');\n"
	        		+ "\n"
	        		+ "INSERT INTO USER \n"
	        		+ "VALUES (null,'sameer','dhakal',28,'USA','CA','San Jose','510-345-7094','bhkfle5@gmail.com','sanM@teo1');\n"
	        		+ "\n"
	        		+ "INSERT INTO USER \n"
	        		+ "VALUES (null,'ram','sharma',29,'USA','CA','San Jose','510-345-7095','bhkfle6@gmail.com','sanM@teo1');\n"
	        		+ "\n"
	        		+ "INSERT INTO USER \n"
	        		+ "VALUES (null,'jairam','patel',55,'USA','CA','San Jose','510-345-7096','bhkfle7@gmail.com','sanM@teo1');\n"
	        		+ "\n"
	        		+ "INSERT INTO USER \n"
	        		+ "VALUES (null,'sita','agrawal',21,'USA','CA','San Jose','510-345-7097','bhkfle8@gmail.com','sanM@teo1');\n"
	        		+ "\n"
	        		+ "INSERT INTO USER \n"
	        		+ "VALUES (null,'jainarayan','bhatt',27,'USA','CA','San Jose','510-345-7098','bhkfle9@gmail.com','sanM@teo1');";
	        
	        stmt.executeUpdate(sql);
	        System.out.println("inserted data in given database..."); 
	        
	        sql = "DROP TABLE IF EXISTS ACCOUNT;\n"
	        		+ "CREATE TABLE ACCOUNT (ACCOUNTID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
	        		+ "ACCOUNTNAME VARCHAR(50),\n"
	        		+ "USERID INT NOT NULL ,\n"
	        		+ "BALANCE DOUBLE(32), \n"
	        		+ "DATECREATED DATE,\n"
	        		+ "CONSTRAINT USERID FOREIGN KEY (USERID)\n"
	        		+ "    REFERENCES USER(USERID)\n"
	        		+ ");";
	        		
	        stmt.executeUpdate(sql);
	        System.out.println("created the  account table  in given database..."); 
	        
	        sql ="insert into account values (null,'Checking',1,5000.0, curdate());\n"
	        		+ "insert into account values (null,'Checking',2,5000.0, curdate());\n"
	        		+ "insert into account values (null,'Saving',3,5000.0, curdate());\n"
	        		+ "insert into account values (null,'Saving',4,5000.0, curdate());\n"
	        		+ "insert into account values (null,'Checking',5,5000.0, curdate());\n"
	        		+ "insert into account values (null,'Checking',6,5000.0, curdate());\n"
	        		+ "insert into account values (null,'Saving',7,5000.0, curdate());\n"
	        		+ "insert into account values (null,'Checking',8,5000.0, curdate());\n"
	        		+ "insert into account values (null,'Checking',9,5000.0, curdate());";
	        
	        stmt.executeUpdate(sql);
	        System.out.println("inserted into the  account table  in given database...");
	        
	        sql = "DROP TABLE IF EXISTS TRANSACTION;\n"
	        		+ "\n"
	        		+ "CREATE TABLE TRANSACTION(TRANSACTIONID LONG PRIMARY KEY AUTO_INCREMENT, USERID INT NOT NULL,\n"
	        		+ " ACCOUNTID INT NOT NULL,DATEOFTXN DATE,\n"
	        		+ " TXNTYPE VARCHAR(200), \n"
	        		+ "CONSTRAINT ACCOUNTID FOREIGN KEY (ACCOUNTID) REFERENCES ACCOUNT(ACCOUNTID)\n"
	        		+ ");\n"
	        		;
	        stmt.executeUpdate(sql);
	        System.out.println("created the  Transaction table  in given database..."); 
	        
	        sql = "insert into transaction values(null,1,1,curdate(),'first account creation');\n"
	        		+ "insert into transaction values(null,2,2,curdate(),'first account creation');\n"
	        		+ "insert into transaction values(null,3,3,curdate(),'first account creation');\n"
	        		+ "insert into transaction values(null,4,4,curdate(),'first account creation');\n"
	        		+ "insert into transaction values(null,5,5,curdate(),'first account creation');\n"
	        		+ "\n"
	        		+ "insert into transaction values(null,6,6,curdate(),'first account creation');\n"
	        		+ "insert into transaction values(null,7,7,curdate(),'first account creation');\n"
	        		+ "insert into transaction values(null,8,8,curdate(),'first account creation');\n"
	        		+ "insert into transaction values(null,9,9,curdate(),'first account creation');";
	        		
	        stmt.executeUpdate(sql);
	        System.out.println("inserted data in  Transaction table  in given database..."); 


	       
	        			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	

	public static Connection getConnection() {

		if(conn == null) {
			makeConnection();
		}

		return conn;
	}
	//NOTE : for better practice , put that insert and create code in a different function 
	


}