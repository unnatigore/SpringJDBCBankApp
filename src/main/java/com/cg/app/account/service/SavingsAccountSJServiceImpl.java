/*
 * package com.cg.app.account.service;
 * 
 * import java.sql.SQLException; import java.util.List; import
 * java.util.logging.Logger;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.jdbc.core.JdbcTemplate; import
 * org.springframework.stereotype.Repository; import
 * org.springframework.transaction.annotation.Transactional;
 * 
 * import com.cg.app.account.SavingsAccount; import
 * com.cg.app.account.dao.SavingsAccountMapper; import
 * com.cg.app.account.util.DBUtil; import
 * com.cg.app.exception.InsufficientFundsException; import
 * com.cg.app.exception.InvalidInputException;
 * 
 * @Repository public class SavingsAccountSJServiceImpl implements
 * SavingsAccountService{
 * 
 * 
 * 
 * @Autowired private JdbcTemplate jdbcTemplate;
 * 
 * public SavingsAccount createNewAccount(SavingsAccount account) throws
 * ClassNotFoundException, SQLException {
 * 
 * Logger logger =
 * Logger.getLogger(SavingsAccountSJServiceImpl.class.getName());
 * logger.info("SJ is Working");
 * 
 * jdbcTemplate.update("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)", new Object[] {
 * account.getBankAccount().getAccountNumber(),
 * account.getBankAccount().getAccountHolderName(),
 * account.getBankAccount().getAccountBalance(), account.isSalary(), null, "SA"
 * }); return null; }
 * 
 * 
 * @Override public SavingsAccount getAccountById(int accountNumber) throws
 * ClassNotFoundException, SQLException { return
 * jdbcTemplate.queryForObject("SELECT * FROM account where account_id=?", new
 * Object[] { accountNumber }, new SavingsAccountMapper());
 * 
 * }
 * 
 * @Override public SavingsAccount fundTransfer()
 * 
 * @Override public SavingsAccount deleteAccount(int accountNumber) throws
 * ClassNotFoundException, SQLException {
 * jdbcTemplate.update("DELETE FROM account WHERE account_id=?", new Object[] {
 * accountNumber }); return null; }
 * 
 * @Transactional(rollbackFor= (Exception.class)) public void
 * fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
 * throws ClassNotFoundException, SQLException { try { withdraw(sender, amount);
 * deposit(receiver, amount);
 * 
 * } catch (InvalidInputException | InsufficientFundsException e) {
 * e.printStackTrace();
 * 
 * } catch (Exception e) { e.printStackTrace();
 * 
 * } }
 * 
 * @Override public SavingsAccount getAccountByName(String accountHolderName)
 * throws ClassNotFoundException, SQLException {
 * 
 * return
 * jdbcTemplate.queryForObject("SELECT * FROM account where account_hn=?", new
 * Object[] { accountHolderName }, new SavingsAccountMapper()); }
 * 
 * 
 * @Override public double checkAccountBalance(int accountNumber) throws
 * ClassNotFoundException, SQLException { return jdbcTemplate.
 * queryForObject("SELECT account_bal FROM account where account_id=?", new
 * Object[] { accountNumber }, Double.class); }
 * 
 * @Override public List<SavingsAccount> getAllSavingsAccount() throws
 * ClassNotFoundException, SQLException {
 * 
 * return jdbcTemplate.query("SELECT * from account", new
 * SavingsAccountMapper()); }
 * 
 * @Override public List<SavingsAccount> sortAllSavingsAccount(int choice)
 * throws ClassNotFoundException, SQLException { String query = null; switch
 * (choice) { case 1: query = "SELECT * FROM account ORDER BY account_id";
 * break;
 * 
 * case 2: query = "SELECT * FROM account ORDER BY account_hn"; break;
 * 
 * case 3: query = "SELECT * FROM account ORDER BY account_bal"; break; }
 * 
 * return jdbcTemplate.query(query, new SavingsAccountMapper()); }
 * 
 * 
 * @Override public SavingsAccount createNewAccount(String accountHolderName,
 * double accountBalance, boolean salary) throws ClassNotFoundException,
 * SQLException { // TODO Auto-generated method stub return null; }
 * 
 * 
 * @Override public List<SavingsAccount> getAccountByBalance(double
 * minmumBalance, double maximumBalance) throws ClassNotFoundException,
 * SQLException { // TODO Auto-generated method stub return null; }
 * 
 * 
 * @Override public void deposit(SavingsAccount account, double amount) throws
 * ClassNotFoundException, SQLException { // TODO Auto-generated method stub
 * 
 * }
 * 
 * 
 * @Override public void withdraw(SavingsAccount account, double amount) throws
 * ClassNotFoundException, SQLException { // TODO Auto-generated method stub
 * 
 * }
 * 
 * 
 * @Override public int updateAccount(int accountNumber, String
 * accountHolderName) throws ClassNotFoundException, SQLException { return
 * jdbcTemplate.update("UPDATE ACCOUNT SET account_hn=? WHERE account_id=?",new
 * Object[] { accountHolderName, accountNumber }); }
 * 
 * 
 * 
 * 
 * 
 * }
 */