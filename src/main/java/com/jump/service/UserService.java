package com.jump.service;

import java.util.List;

import com.jump.dao.UserDAO;
import com.jump.dao.UserDAOImpl;
import com.jump.model.User;

public class UserService {
	
	UserDAO userDAO = new UserDAOImpl();
	
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
	
	public boolean checkInvalidUserId(int userId) {
		
		List<Integer> users = userDAO.selectAllUsers();
		
		for(int i=0;i<users.size(); i++) {
			if (userId==users.get(i)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	
	

}
