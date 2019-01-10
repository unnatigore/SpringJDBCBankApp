package com.cg.app.account.dao;
	
	import java.sql.SQLException;
	import java.util.List;
	import java.util.Set;
	
	import com.cg.app.account.SavingsAccount;
	import com.cg.app.exception.AccountNotFoundException;;
	
	public interface SavingsAccountDAO {
	
		SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException;
	
		int updateAccount(int accountNumber, String accountHolderName) throws ClassNotFoundException, SQLException;
	
		SavingsAccount getAccountById(int accountNumber)
				throws ClassNotFoundException, SQLException, AccountNotFoundException;
	
		SavingsAccount deleteAccount(int accountNumber)
				throws ClassNotFoundException, SQLException, AccountNotFoundException;
	
		SavingsAccount getAccountByName(String accountHolderName)
				throws AccountNotFoundException, ClassNotFoundException, SQLException;
	
		List<SavingsAccount> getAccountByBalance(double minmumBalance, double maximumBalance)
				throws ClassNotFoundException, SQLException, AccountNotFoundException;
	
		double checkAccountBalance(int accountNumber) throws AccountNotFoundException, ClassNotFoundException, SQLException;
	
		List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;
	
		void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	
		List<SavingsAccount> sortAllSavingsAccount(int choice) throws ClassNotFoundException, SQLException;
	
		//void commit() throws SQLException;
	
	}
