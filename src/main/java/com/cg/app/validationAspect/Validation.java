/*
 * package com.cg.app.validationAspect;
 * 
 * import java.util.logging.Logger;
 * 
 * import org.aspectj.lang.ProceedingJoinPoint; import
 * org.aspectj.lang.annotation.Around;
 * 
 * import com.cg.app.account.SavingsAccount;
 * 
 * public class Validation {
 * 
 * private Logger logger = Logger.getLogger(Validation.class.getName());
 * 
 * @Around("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.deposit(..))"
 * ) public void log1(ProceedingJoinPoint pjp) throws Throwable {
 * logger.info("In deposite method"); Object[] params = pjp.getArgs(); double
 * amount = (double) params[0]; SavingsAccount account = (SavingsAccount)
 * params[0]; Double currentBalance =
 * account.getBankAccount().getAccountBalance(); if (amount > 0) {
 * logger.info("Deposite Successful"); pjp.proceed(); } else {
 * logger.info("Invallid Amount"); } }
 * 
 * }
 */