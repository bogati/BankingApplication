package com.jump.model;

import java.time.LocalDate;

public class Transaction {
	long transactionId;
	int userId;
	int accountId;
	LocalDate dateOfTxn;
	String txnType; 
	
	public Transaction(long transactionId, int userId, int accountId, LocalDate dateOfTxn, String txnType) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.accountId = accountId;
		this.dateOfTxn = dateOfTxn;
		this.txnType = txnType;
	}
	
	
	
	
	public Transaction(int userId, int accountId, LocalDate dateOfTxn, String txnType) {
		super();
		this.userId = userId;
		this.accountId = accountId;
		this.dateOfTxn = dateOfTxn;
		this.txnType = txnType;
	}




	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public LocalDate getDateOfTxn() {
		return dateOfTxn;
	}
	public void setDateOfTxn(LocalDate dateOfTxn) {
		this.dateOfTxn = dateOfTxn;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}




	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", userId=" + userId + ", accountId=" + accountId
				+ ", dateOfTxn=" + dateOfTxn + ", txnType=" + txnType + "]";
	}
	
	
	

}
