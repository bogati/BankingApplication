package com.jump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jump.console.UserInterface;
import com.jump.util.ConnectionManager;


/**
 * This Application demonstrate working of banking application with console as UI
 * @author bimala bogati
 *
 */

@SpringBootApplication
public class BankingConsoleApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(BankingConsoleApplication.class, args);
		
		//------------------------------------------------------------------------------------------//
		//Get the connection and create tables and add some users on the start of the program 
		ConnectionManager.getConnection();
		
		
		
		//------------------------------------------------------------------------------------------//
		//Display the landing page
		
		UserInterface.displayLandingMessage();
		
		
		
		
		
		
		
	}

}
