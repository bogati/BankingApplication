package com.jump.dao;

import java.util.List;

import com.jump.model.User;

public interface UserDAO {
	
	//Read account from database using accountId
	public  User selectUserById(int userId);
	
	//Create a user
	public  void insertUser(User user);
	
	public int selectLastInsertedUserId();

	

	
	public List<Integer> selectAllUsers();
	
	
	

}
