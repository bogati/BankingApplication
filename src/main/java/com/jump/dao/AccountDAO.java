package com.jump.dao;

import com.jump.model.Account;

public interface AccountDAO {
	
	public  Account selectAccountById(int accountId);
	public int selectLastInsertedAccountId();
	public Account selectAccountByUserId(int userId);
	
	public void insertAccount(Account account);
	
	public void updateBalance(double amount, int accountId);

}
