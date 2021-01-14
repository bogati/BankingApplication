package com.jump.dao;

import com.jump.model.User;
import com.jump.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAOImpl implements UserDAO{
	
	Connection conn = ConnectionManager.getConnection();
	
	
	String firstName;
	String lastName;
	int age;
	String addrCountry;
	String addrState;
	String addrCity;
	String phoneNumber;
	String email;
	String password;
	
	
	private static final String SELECT_USERBYID= "SELECT * FROM USER WHERE USERID = ?";//is null 
	private static final String INSERT_USER = "INSERT INTO USER VALUES (null,?,?,?,?,?,?,?,?,?) ";
	private static final String SELECT_LAST_INSERTED_USER = "SELECT MAX(userId) FROM USER ";
	

	@Override
	//accountId is unique, hence no need to pass in the userid 
	public  User selectUserById(int userId) {
		User user = null;
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_USERBYID)){
			
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			//LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(5)) );

			user = new User(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getInt(4),
						    rs.getString(5),rs.getString(6),rs.getString(7),
						    rs.getString(8),rs.getString(9),rs.getString(10));
			}
			
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
		
		return user;
			
	}
	@Override
	public  void insertUser(User user) {
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_USER)){
			
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setInt(3, user.getAge());
			pstmt.setString(4, user.getAddrCountry());
			pstmt.setString(5, user.getAddrState());
			pstmt.setString(6, user.getAddrCity());
			pstmt.setString(7,user.getPhoneNumber());
			pstmt.setString(8, user.getEmail());
			pstmt.setString(9, user.getPassword());
			
			//System.out.println("the user name is "+user.getFirstName());
			
			pstmt.executeUpdate();
			
			System.out.println ("The user insert execution output value is ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public int selectLastInsertedUserId() {
		
		ResultSet rs = null;
		int maxval=0;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_LAST_INSERTED_USER)){
			rs = pstmt.executeQuery();
			rs.next();
			maxval = rs.getInt(1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxval;
		
	}

	

	@Override
	public void displayLastFiveTransactions() {
		
		
	}
	
	@Override
	public void loginToSystem(int userId, String password) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void logoutFromSystem() {
		
		
	}
	
	//for the purpose of testing the functions 
	
	public static void main(String args[]) {
		UserDAO userDAO = new UserDAOImpl();
		System.out.println(userDAO.selectLastInsertedUserId());
		
	}



	



	

}
