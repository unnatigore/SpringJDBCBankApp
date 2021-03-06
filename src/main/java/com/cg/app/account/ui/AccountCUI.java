package com.cg.app.account.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.service.SavingsAccountService;
import com.cg.app.account.service.SavingsAccountServiceImpl;
//import com.cg.app.account.util.DBUtil;
import com.cg.app.exception.AccountNotFoundException;

@Component
public class AccountCUI {
	private static Scanner scanner = new Scanner(System.in);

	@Autowired
	private SavingsAccountService savingsAccountService;
	
	

	public void start() {

		do {
			System.out.println("****** Welcome to Money Money Bank********");
			System.out.println("1. Open New Savings Account");
			System.out.println("2. Update Account");
			System.out.println("3. Delete Account");
			System.out.println("4. Check Current Balance");
			System.out.println("5. Search Account By Account Number");
			System.out.println("6. Search Account By Account Holder Name");
			System.out.println("7. Search Account By Account Balance");
			System.out.println("8. Get All Savings Account Details");
			System.out.println("9. Sort Accounts");
			System.out.println("10. Withdraw");
			System.out.println("11. Deposit");
			System.out.println("12. FundTransfer");
			System.out.println("13. Exit");
			System.out.println();
			System.out.println("Make your choice: ");

			int choice = scanner.nextInt();

			performOperation(choice);

		} while (true);
	}

	private void performOperation(int choice) {
		switch (choice) {
		case 1:
			acceptInput("SA");
			break;

		case 2:
			updateAccount();
			break;

		case 3:
			deleteAccount();
			break;

		case 4:
			checkCurrentAccountBalance();
			break;
			
		case 5:
			searchAccountById();
			break;

			
		case 6:
			searchAccountByName();
			break;

			

		case 7:
			searchAccountByBalance();
			break;
			
		case 8:
			showAllAccounts();
			break;
			

		case 9:
			sortAccounts();
			break;
			
		case 10:
			withdraw();
			break;


		case 11:
			deposit();
			break;

		case 12:
			fundTransfer();
			break;


		case 13:
			System.exit(0);
			break;
		default:
			System.err.println("Invalid Choice!");
			break;
		}

	}


	private void updateAccount() {

		System.out.println("Enter Account Number");
		int accountNumber = scanner.nextInt();

		System.out.println("Enter new Account Holder Name");
		String accountHolderName = scanner.nextLine();
		accountHolderName = scanner.nextLine();
		try {
			savingsAccountService.updateAccount(accountNumber, accountHolderName);
			System.out.println("Your Account is updated successfully!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchAccountByBalance() {
		System.out.println("Enter Minimum Balance");
		double minmumBalance = scanner.nextDouble();
		System.out.println("Enter Maximum Balance");
		double maximumBalance = scanner.nextDouble();
		try {
			List list = savingsAccountService.getAccountByBalance(minmumBalance, maximumBalance);
			System.out.println(list);
		} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void checkCurrentAccountBalance() {
		System.out.println("Enter account Number");
		int accountNumber = scanner.nextInt();
		try {
			double accountBalance = savingsAccountService.checkAccountBalance(accountNumber);
			System.out.println("Current Balance=" + accountBalance);
		} catch (ClassNotFoundException | AccountNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private void searchAccountByName() {
		System.out.println("Enter account Holder Name");
		String accountHolderName = scanner.next();

		try {
			SavingsAccount savingsAccount = savingsAccountService.getAccountByName(accountHolderName);
			System.out.println(savingsAccount);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void searchAccountById() {
		System.out.println("Enter account Number:");
		int accountNumber = scanner.nextInt();
		try {

			SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
			System.out.println(savingsAccount);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void deleteAccount() {
		System.out.println("Enter Account Number");
		int accountNumber = scanner.nextInt();
		try {
			savingsAccountService.deleteAccount(accountNumber);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (AccountNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void fundTransfer() {
		System.out.println("Enter Account Sender's Number: ");
		int senderAccountNumber = scanner.nextInt();
		System.out.println("Enter Account Receiver's Number: ");
		int receiverAccountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		try {
			SavingsAccount senderSavingsAccount = savingsAccountService.getAccountById(senderAccountNumber);
			SavingsAccount receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
			savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deposit() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		SavingsAccount savingsAccount = null;
			try {
				savingsAccount = savingsAccountService.getAccountById(accountNumber);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				savingsAccountService.deposit(savingsAccount, amount);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	private void withdraw() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		SavingsAccount savingsAccount = null;
		try {
			savingsAccount = savingsAccountService.getAccountById(accountNumber);
			savingsAccountService.withdraw(savingsAccount, amount);
		//	DBUtil.commit();
		} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void showAllAccounts() {
		List<SavingsAccount> savingsAccounts;
		try {
			savingsAccounts = savingsAccountService.getAllSavingsAccount();
			for (SavingsAccount savingsAccount : savingsAccounts) {
				System.out.println(savingsAccount);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private void acceptInput(String type) {
		if (type.equalsIgnoreCase("SA")) {
			System.out.println("Enter your Full Name: ");
			String accountHolderName = scanner.nextLine();
			accountHolderName = scanner.nextLine();
			System.out.println("Enter Initial Balance(type na for Zero Balance): ");
			String accountBalanceStr = scanner.next();
			double accountBalance = 0.0;
			if (!accountBalanceStr.equalsIgnoreCase("na")) {
				accountBalance = Double.parseDouble(accountBalanceStr);
			}
			System.out.println("Salaried?(y/n): ");
			boolean salary = scanner.next().equalsIgnoreCase("n") ? false : true;
			createSavingsAccount(accountHolderName, accountBalance, salary);
		}
	}

	private void createSavingsAccount(String accountHolderName, double accountBalance, boolean salary) {
		try {
			savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void sortAccounts() {
		List<SavingsAccount> sortAllAccounts;
		System.out.println("Sort accounts by \n 1.Account Number \n 2.Name \n 3.Account Balance");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			try {
				sortAllAccounts = savingsAccountService.sortAllSavingsAccount(choice);
				System.out.println(sortAllAccounts);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;

		case 2:
			try {
				sortAllAccounts = savingsAccountService.sortAllSavingsAccount(choice);
				System.out.println(sortAllAccounts);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;

		case 3:
			try {
				sortAllAccounts = savingsAccountService.sortAllSavingsAccount(choice);
				System.out.println(sortAllAccounts);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}

}