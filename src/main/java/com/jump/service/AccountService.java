package com.jump.service;

import java.time.LocalDate;
import java.util.List;

import com.jump.dao.AccountDAO;
import com.jump.dao.AccountDAOImpl;
import com.jump.dao.TransactionDAO;
import com.jump.dao.TransactionDAOImpl;
import com.jump.model.Account;
import com.jump.model.Transaction;

public class AccountService {
	
	AccountDAO accountDAO = new AccountDAOImpl();
	TransactionDAO transactionDAO = new TransactionDAOImpl();
	
	
	
	//Business Logic : If money withdrawn from account , reduce the balance in the account 
		
		
		//insert operation into Account /deposit in the account 
		public void depositMoney(double amount, int accountId, int userId) {
			
			 Account acc = accountDAO.selectAccountById(accountId);
			 acc.setBalance(acc.getBalance()+amount);
			 
			 //this logic is incorrect as you should not be able to see the balance onto which you make deposit if that deposit belongs to other user!
			 //System.out.println("\n The new balance after deposit is : "+acc.getBalance());
			 
			 //once the deposit is complete update the change in database as well 
			 accountDAO.updateBalance(acc.getBalance(),accountId);
			 
			 Transaction transaction = new Transaction(userId,accountId,LocalDate.now(),"Deposit of amount "+amount);
				
			 transactionDAO.registerTransaction(transaction);
			
		}
		
		//update operation, withdrawal 
		public boolean withdrawMoney(double amount, int accountId, int userId) {
			
			Account acc = accountDAO.selectAccountById(accountId);
			
			if(amount > acc.getBalance()) {
				System.out.println("Sorry the amount that you requested to withdraw is greater than your balance! \n");
				System.out.println("The total available balance in your account is : "+acc.getBalance());
				
				Transaction transaction = new Transaction(userId,accountId,LocalDate.now(),"withdrawal/Transfer of amount "+amount+" unsuccessful !");
				
				transactionDAO.registerTransaction(transaction);
				 
				return false;
			}
			
			acc.setBalance(acc.getBalance()- amount);
			System.out.println("\n The new balance after withdraw is : "+acc.getBalance());
			
			 //once the withdrawal is complete update the change in database as well 
			accountDAO.updateBalance(acc.getBalance(),accountId);
			
			Transaction transaction = new Transaction(userId,accountId,LocalDate.now(),"withdrawal/Transfer of amount "+amount +"successful !");
			
			 transactionDAO.registerTransaction(transaction);
			 
			 return true;
			
		}
		
		public boolean checkInvalidAccountId(int accountId) {
			List<Integer> accounts = accountDAO.selectAllAccounts();
			
			for(int i=0;i<accounts.size(); i++) {
				if (accountId==accounts.get(i)) {
					return true;
				}
			}
			
			return false;
			
			
			
			
		}
	
}
