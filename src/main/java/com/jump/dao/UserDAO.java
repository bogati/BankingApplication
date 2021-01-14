package com.jump.dao;

import com.jump.model.User;

public interface UserDAO {
	
	//Read account from database using accountId
	public  User selectUserById(int userId);
	
	//Create a user
	public  void insertUser(User user);
	
	public int selectLastInsertedUserId();
	

	
	//select, last 5 transactions 
	public  void displayLastFiveTransactions();
	
	public  void loginToSystem(int userId, String password);
	
	public  void logoutFromSystem();
	
	
	

}
