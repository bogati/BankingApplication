package com.jump.model;

import java.time.LocalDate;

public class Account {
	
	int accountId;
	//fk : points to pk of User
	String accountName;
	int userId;
	double balance;
	LocalDate dateCreated;
	
	public Account(int accountId, String accountName, int userId, double balance, LocalDate dateCreated) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.userId = userId;
		this.balance = balance;
		this.dateCreated = dateCreated;
	}
	
	public Account(String accountName, int userId, double balance, LocalDate dateCreated) {
		super();
		this.accountName = accountName;
		this.userId = userId;
		this.balance = balance;
		this.dateCreated = dateCreated;
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public LocalDate getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	} 
	
	

}
