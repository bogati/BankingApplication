package com.jump.console;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.jump.dao.AccountDAO;
import com.jump.dao.AccountDAOImpl;
import com.jump.dao.TransactionDAO;
import com.jump.dao.TransactionDAOImpl;
import com.jump.dao.UserDAO;
import com.jump.dao.UserDAOImpl;
import com.jump.model.Account;
import com.jump.model.Transaction;
import com.jump.model.User;
import com.jump.service.AccountService;
import com.jump.service.UserService;



public class UserInterface {
	
	public static final Scanner scan = new Scanner(System.in);
	
	//declaring it static because the static method has access to static variables and static functions only 
	  static UserDAO userDAO = new UserDAOImpl();;
	  static AccountDAO accountDAO = new AccountDAOImpl();
	  static TransactionDAO transactionDAO = new TransactionDAOImpl();
	  static AccountService accountService = new AccountService();
	 
	/*
	public UserInterface() {
		userDAO = new UserDAOImpl();
		accountDAO = new AccountDAOImpl();
		
	}
	*/
	
	
	
	
	public static  void displayLandingMessage() {
	
		String result = "+-------------------------------------+ \n\n"+
						"|      DOLLARSBANK Welcomes you !     | \n\n"+
						"+-------------------------------------+\n\n" +
						"1. Create a new Account \n" +
						"2. Login \n"+
						"3. Exit \n\n" +
						"please enter one of the choices above (1,2, or 3) \n" ;
						
	
	  System.out.println(result);
	  
	  getUserInput();
	  
	  
	}
	
	/*------------------------------------------------------------------------------------------*/

	
	//the new user information will be stored along with the Account information //DO VALIDATIONS PART LATER 
	
	public  static void createNewUser() {
		
		System.out.println("+-------------------------------------+") ;
		System.out.println("| Enter details for new Account !     |") ;
		System.out.println("+-------------------------------------+") ;
		
		
		//userId will be automatically assigned by the system , so grab the userid from the system
		
		System.out.println("Customer First Name: \n");
		String firstName = scan.nextLine();
		System.out.println("Customer Last Name: \n");
		String lastName = scan.nextLine();
		System.out.println("Customer Age: \n");
		int age = scan.nextInt();
		scan.nextLine();
		System.out.println("Customer Country: \n");
		String addrCountry = scan.nextLine();
		System.out.println("Customer State: \n");
		String addrState = scan.nextLine();
		System.out.println("Customer City: \n");
		String addrCity = scan.nextLine();
		System.out.println("Customer Phone: please insert a '-' after third and after sixth digit eg. 510-789-7658\n");
		String phone = scan.nextLine();
		System.out.println("Customer email: \n");
		String email = scan.nextLine();
		System.out.println("Customer Account password: \n");
		String password = scan.nextLine();
		
		//once you have all the user details, persist the user in the database 
		User user = new User(firstName, lastName, age, addrCountry, addrState, addrCity, phone, email,password);
		
		//responsibility of interaction with db is of DAO -- call the function here 
		
		userDAO.insertUser(user);
		System.out.println("The User profile has been created successfully !");
		
		/* after finishing creating the user profile, create an account for the user 
		   select the last inserted user and grab its userid and pass it to the createAccount function
		*/ 
		createBankAccount(userDAO.selectLastInsertedUserId(),0);
		
	}
	/* ------------------------------------------------------------------------------------------ */

	
	
	public  static void createBankAccount(int userId, int flag) {

		
		
		System.out.println("\nYou MUST fill out the following information for bank account creation ");
		
		//accountId will be assigned automatically 
		System.out.println("Account Type : for ex Savings, Checkings etc");
		String accountName = scan.nextLine();
		System.out.println("\nAccount Balance: ");
		Double balance = scan.nextDouble();
		scan.nextLine();
		
		Account account = new Account(accountName,userId,balance, LocalDate.now());
		accountDAO.insertAccount(account);
		
		System.out.println("The account has been created successfully ! \n\n");
		
		System.out.println("please briefly describe the transaction type for ex : first account, additional account creation, deposit, withdawal etc");
		String txnType = scan.nextLine();
		//once the bank account is created , register that transaction into Transaction table
		
		Transaction transaction = new Transaction(userId,accountDAO.selectLastInsertedAccountId(),LocalDate.now(),txnType);
		
		transactionDAO.registerTransaction(transaction);
		//System.out.println("\nThe Transaction has been created successfully registered to Transaction table!");
		
		if(flag==0) {
		displayLandingMessage();
		}
		
		
	}
	
	/* ------------------------------------------------------------------------------------------ */
	public  static void displayWelcomeAfterLogin(int userId) {
		String result = "+-------------------------------------+ \n\n"+
						"|    WELCOME You are now logged in !  | \n\n"+
						"+-------------------------------------+ \n\n" 
						+ "1. Deposit\n"
						+ "2. Withdrawl\n"
						+ "3. Funds Transfer\n"
						+ "4. recent transaction history\n"
						+ "5. Display customer information\n"
						+ "6. Add new account \n"
						+ "7. Sign out\n\n"
						+ "Please make a choice of services you wish to use (your options are : 1,2,3,4,5,6,7)";
		
		
		System.out.println(result);
		
		//take care of all the available services
		handleUserServices(userId);
		 
		 
			
	}
	/* ------------------------------------------------------------------------------------------ */

	
	public static void loginUser(int userId, String password) {
		
		//log the user in 
		UserService userService = new UserService();
		while(userService.validateLoginCredentials(userId, password)==false) {
			System.out.println("Invalid Credentials , please try again! ");
			
			System.out.println("please enter the user Id : ");
			userId = scan.nextInt();
			scan.nextLine();
			System.out.println("please enter the password : ");
			password = scan.nextLine();
		}
		
		
			//log the user in and display the login welcome message 
			//System.out.println("user login successful !");
			displayWelcomeAfterLogin(userId);
			
			
		
	}
	//------------------------------------------------------------------------------------------//

	
	//once user is logged in , show the welcome message !
	
	public  void welcomeUserMessage() {
		
	}
	
	
	//------------------------------------------------------------------------------------------//
	public static void getUserInput() {
		int num = scan.nextInt();
		scan.nextLine();
		
		//handle each options provided by the user here 
		
		if(num==1) {
			//create a new account, call create new account function 
			createNewUser();
			
			
		}
		else if(num==2) {
			//login 
			System.out.println("please enter the user Id : ");
			int userId = scan.nextInt();
			scan.nextLine();
			System.out.println("please enter the password : ");
			String password = scan.nextLine();
			loginUser(userId, password);
			
		}else {
			// 3 exit 
			System.out.println("Thank you for visting us ! see you soon !");
		}
	}
	
	//------------------------------------------------------------------------------------------//
	
	//TEST CASES LATER 
	
	public  static void handleUserServices(int userId) {
		
		int choice = scan.nextInt(); 
		 scan.nextLine();
		 
		 while(choice != 7) {
			 
		if(choice ==7) {
			System.out.println("You are logged out now !");
			displayLandingMessage();
			
		}
		 
		else if(choice==1) {
			 //deposit 
			 System.out.println("\n please enter accountId to which you wish to deposit the amount : \n");
			 int accountId = scan.nextInt();
			 scan.nextLine();
			 System.out.println("please enter the amount you wish to deposit: ");
			 double amount = scan.nextDouble();
			 scan.nextLine();
			 
			 accountService.depositMoney(amount,accountId,userId);
			 
			 
				//once the bank account is created , register that transaction into Transaction table
				
			
			 
			
			 
		 }
		 else if (choice==2) {
			 //deposit 
			 System.out.println("\n please enter accountId to which you wish to withdraw the amount from : \n");
			 int accountId = scan.nextInt();
			 scan.nextLine();
			 System.out.println("please enter the amount you wish to withdraw: ");
			 double amount = scan.nextDouble();
			 scan.nextLine();
			 
			 accountService.withdrawMoney(amount,accountId,userId);
				
			 
			 
			
			 
		 }
		
		 else if(choice==3) {
			 //Funds transfer
			 
		 }
		 
		 
		 
		 
		 else if(choice==4) {
			 List<Transaction> transactions = transactionDAO.displayLastFiveTransactions(userId);
			 System.out.println("Following are your last five Transactions");
			 System.out.println("Transaction Id | User Id | Account Id | Date Of Transaction | Transaction Type \n" );
			 for(int i=0 ; i<transactions.size();i++ ) {
				 
				 System.out.println(transactions.get(i).getTransactionId()+"              |  "+
						 			"  "+transactions.get(i).getUserId()+"   |   "+transactions.get(i).getAccountId()+ " "+
						 			"             |  " + transactions.get(i).getDateOfTxn()+"        |"+transactions.get(i).getTxnType());
				 
			 }
			 
		 
		 }
		
		 else if (choice==5) {
			 //display the customer information
		 }
		 else if (choice==6) {
			 createBankAccount(userId,1);
			 
		 }
		 
		 System.out.println("Please make another choice of services you wish to use (your options are : 1,2,3,4,5,6 or 7)");
		 choice = scan.nextInt(); 
		 scan.nextLine();
		 
		}
		
		 
		 displayLandingMessage();
		 
		 
		
		
	}
	//------------------------------------------------------------------------------------------//

	
	//RESOURCES:
	//https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
	

}
