package com.cg.app.account.service;
	
	import java.sql.SQLException;
	import java.util.List;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import com.cg.app.account.SavingsAccount;
	import com.cg.app.account.dao.SavingsAccountDAO;
	import com.cg.app.account.dao.SavingsAccountDAOImpl;
	import com.cg.app.account.factory.AccountFactory;
	import com.cg.app.account.util.DBUtil;
	import com.cg.app.exception.AccountNotFoundException;
	import com.cg.app.exception.InsufficientFundsException;
	import com.cg.app.exception.InvalidInputException;
	
	@Service
	public class SavingsAccountServiceImpl implements SavingsAccountService {
	
		private AccountFactory factory;
	
		@Autowired
		private SavingsAccountDAO savingsAccountDAO;
	
		@Autowired
		public SavingsAccountServiceImpl() {
			factory = AccountFactory.getInstance();
			/* this.savingsAccountDAO = savingsAccountDAO; */
		}
	
		public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
				throws ClassNotFoundException, SQLException {
			SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
			savingsAccountDAO.createNewAccount(account);
			return null;
		}
	
		public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
			return savingsAccountDAO.getAllSavingsAccount();
		}
	
		public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
			if (amount > 0) {
				double currentBalance = account.getBankAccount().getAccountBalance();
				currentBalance += amount;
				savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
				// savingsAccountDAO.commit();
			} else {
				throw new InvalidInputException("Invalid Input Amount!");
			}
		}
	
		public void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
			double currentBalance = account.getBankAccount().getAccountBalance();
			if (amount > 0 && currentBalance >= amount) {
				currentBalance -= amount;
				savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
				// savingsAccountDAO.commit();
			} else {
				throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
			}
		}
	
		public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
				throws ClassNotFoundException, SQLException {
			try {
				withdraw(sender, amount);
				deposit(receiver, amount);
				DBUtil.commit();
			} catch (InvalidInputException | InsufficientFundsException e) {
				e.printStackTrace();
				DBUtil.rollback();
			} catch (Exception e) {
				e.printStackTrace();
				DBUtil.rollback();
			}
		}
	
		/*
		 * @Override public SavingsAccount updateAccount(SavingsAccount account) {
		 * 
		 * return null; }
		 */
	
		public SavingsAccount getAccountById(int accountNumber)
				throws ClassNotFoundException, SQLException, AccountNotFoundException {
			return savingsAccountDAO.getAccountById(accountNumber);
		}
	
		public SavingsAccount getAccountByName(String accountHolderName)
				throws ClassNotFoundException, SQLException, AccountNotFoundException {
	
			return savingsAccountDAO.getAccountByName(accountHolderName);
		}
	
		public SavingsAccount deleteAccount(int accountNumber)
				throws ClassNotFoundException, SQLException, AccountNotFoundException {
	
			return savingsAccountDAO.deleteAccount(accountNumber);
		}
	
		public double checkAccountBalance(int accountNumber)
				throws AccountNotFoundException, ClassNotFoundException, SQLException {
			return savingsAccountDAO.checkAccountBalance(accountNumber);
		}
	
		public List<SavingsAccount> getAccountByBalance(double minmumBalance, double maximumBalance)
				throws ClassNotFoundException, SQLException, AccountNotFoundException {
	
			return savingsAccountDAO.getAccountByBalance(minmumBalance, maximumBalance);
		}
	
		public int updateAccount(int accountnumber, String newAccountHolderName)
				throws ClassNotFoundException, SQLException {
	
			return savingsAccountDAO.updateAccount(accountnumber, newAccountHolderName);
		}
	
		public List<SavingsAccount> sortAllSavingsAccount(int choice) throws ClassNotFoundException, SQLException {
			return savingsAccountDAO.sortAllSavingsAccount(choice);
		}
	
	}
