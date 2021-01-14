package com.jump.service;

import com.jump.dao.UserDAOImpl;
import com.jump.model.User;

public class UserService {
	
	//Business Logic: If userid/pw does not match , deny login
	
	public static boolean validateLoginCredentials(int userId, String password) {
		
		//if login credentials matches to the one in the db then allow to login 
		//select the user by id 
		UserDAOImpl daoObj = new UserDAOImpl(); 
		
		User user = daoObj.selectUserById(userId); 
		
		
		if(user!=null && user.getPassword().equals(password)) {
			//System.out.println("The user name and the password entered matched with that of db");
			return true;
		}
		
		
		return false; 
		
	}
	
	
	
	

}
