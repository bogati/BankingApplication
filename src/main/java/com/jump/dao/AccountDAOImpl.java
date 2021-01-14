package com.jump.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import com.jump.model.Account;
import com.jump.util.ConnectionManager;

public class AccountDAOImpl implements AccountDAO{
	
	Connection conn = ConnectionManager.getConnection();
	ResultSet rs = null;
	
	
	private static final String SELECT_ACCOUNTBYID = "SELECT * FROM ACCOUNT WHERE ACCOUNTID = ?";//is null
	private static final String INSERT_ACCOUNT = "INSERT INTO ACCOUNT VALUES(null,?,?,?,?)";
	private static final String SELECT_LAST_INSERTED_ACCOUNTID = "SELECT MAX(accountId) FROM ACCOUNT ";
	private static final String SELECT_ACCOUNT_BYUSERID = "SELECT * FROM ACCOUNT WHERE USERID=?";
	private static final String UPDATE_BALANCE = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNTID=?";

	@Override
	//accountId is unique, hence no need to pass in the userid 
	public Account selectAccountById(int accountId) {
		Account acc = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ACCOUNTBYID)){
			
			pstmt.setInt(1, accountId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(5)) );

			acc = new Account(rs.getInt(1), rs.getString(2),rs.getInt(3),
						  rs.getDouble(4),localDate);
			}
			
		}
		catch (SQLException e) {
		e.printStackTrace();
		}
		
		return acc;
			
	}


	@Override
	public void insertAccount(Account account) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_ACCOUNT)){
			
			pstmt.setString(1, account.getAccountName());
			pstmt.setInt(2, account.getUserId());
			pstmt.setDouble(3, account.getBalance());
			
			Date date = java.sql.Date.valueOf(account.getDateCreated());

			pstmt.setDate(4,date);
			pstmt.executeUpdate();
			
			//System.out.println ("The Account execution output value is ");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	@Override
	public int selectLastInsertedAccountId() {
		
			
			ResultSet rs = null;
			int maxval=0;
			
			try(PreparedStatement pstmt = conn.prepareStatement(SELECT_LAST_INSERTED_ACCOUNTID)){
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
	public Account selectAccountByUserId(int userId) {
		
		Account acc = null;
		ResultSet rs = null;
		
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ACCOUNT_BYUSERID)){
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			rs.next();
			
			LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate(5)) );

			acc = new Account(rs.getInt(1), rs.getString(2),rs.getInt(3),
						  rs.getDouble(4),localDate);
				
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return acc;
		
	}


	@Override
	public void updateBalance(double amount, int accountId) {
		
		try(PreparedStatement pstmt = conn.prepareStatement(UPDATE_BALANCE)){
			pstmt.setDouble(1, amount);
			pstmt.setInt(2, accountId);
			pstmt.executeUpdate();
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	


}
