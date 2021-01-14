package com.jump.dao;

import java.util.List;

import com.jump.model.Account;
import com.jump.model.User;

public interface AccountDAO {
	
	public  Account selectAccountById(int accountId);
	
	public int selectLastInsertedAccountId();
	
	public List<Account> selectAccountsByUserId(int userId);
	
	public User selectUserByAccountId(int accountId);
	
	public void insertAccount(Account account);
	
	public void updateBalance(double amount, int accountId);

	public List<Integer> selectAllAccounts();

}
