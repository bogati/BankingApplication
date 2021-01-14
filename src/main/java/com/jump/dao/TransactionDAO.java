package com.jump.dao;

import java.util.List;

import com.jump.model.Transaction;

public interface TransactionDAO {

	public void registerTransaction(Transaction txn);
	public List<Transaction> displayLastFiveTransactions(int userId);

}
