package com.tka.operation;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tka.config.HibernateConfig;
import com.tka.entity.BankAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Operation {
	SessionFactory sf = HibernateConfig.getSessionFactory();
	Scanner scanner = new Scanner(System.in);

	public String createAccount() {
		System.out.print("Enter Account Number : ");
		String accountNumber = scanner.nextLine();

		System.out.print("Enter Account Holder Name : ");
		String accountHolderName = scanner.nextLine();

		System.out.print("Enter Account Balance : ");
		double balance = scanner.nextDouble();

		System.out.print("Enter Account Type : ");
		String accountType = scanner.next();

		System.out.print("Enter Account Currency : ");
		String currency = scanner.next();

		BankAccount account = new BankAccount(accountNumber, accountHolderName, balance, accountType, currency);

		Session session = sf.openSession();
		session.save(account);
		session.beginTransaction().commit();
		session.close();

		return "account created successfully with acc number:" + account.getAccountNumber();

	}

	public String deleteAccount() {
		System.out.print("Enter Account Number to delete: ");
		String accountNumber = scanner.nextLine();

		Session session = sf.openSession();
		BankAccount account = session.get(BankAccount.class, accountNumber);
		session.delete(account);
		session.beginTransaction().commit();
		session.close();

		return "account created successfully with acc number:" + account.getAccountNumber();

	}

	public String updateAccount() {

		System.out.print("Enter Account Number to update: ");
		String accountNumber = scanner.nextLine();

		Session session = sf.openSession();
		BankAccount account = session.get(BankAccount.class, accountNumber);

		System.out.print("Enter New Account Holder Name : ");
		String accountHolderName = scanner.nextLine();

		System.out.print("Enter New Account Balance : ");
		double balance = scanner.nextDouble();

		System.out.print("Enter New Account Type : ");
		String accountType = scanner.next();

		System.out.print("Enter New Account Currency : ");
		String currency = scanner.next();

		account.setAccountHolderName(accountHolderName);
		account.setBalance(balance);
		account.setAccountType(accountType);
		account.setCurrency(currency);

		session.update(account);
		session.beginTransaction().commit();
		session.close();

		return "account updated successfully with acc number: " + account.getAccountNumber();
	}

	public String getAccount() {
		System.out.print("Enter Account Number to fetch: ");
		String accountNumber = scanner.nextLine();

		Session session = sf.openSession();
		BankAccount account = session.get(BankAccount.class, accountNumber);
		session.close();
		return "account updated successfully with acc number: " + account.getAccountNumber();

	}

	public List<BankAccount> getBankAccountByGivenIds() {
		Session session = sf.openSession();
		List<String> accountNumbers = new ArrayList<String>();
		System.out.println("How many");
		List<BankAccount> accounts = session.byMultipleIds(BankAccount.class).multiLoad("10001", "10002");
		return accounts;
	}

	public List<BankAccount> getAllBankAccounts() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		List<BankAccount> list = criteria.list();
		return list;
	}

//user want to retrive all accounts in ascending order as per accholdername
	public List<BankAccount> getAllBankAccountInASC() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);

		criteria.addOrder(Order.asc("accountHolderName")); // Limit to first 5 records
		List<BankAccount> list = criteria.list();
		return list;
	}

//user want to retrive first five records
	public List<BankAccount> getFirstfiveAccounts() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setMaxResults(5); // Limit to first 5 records
		List<BankAccount> list = criteria.list();
		return list;
	}

//user want to retrive a specific number of records starting from a particular row
	public List<BankAccount> getSpecificRecord() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setFirstResult(10);
		criteria.setMaxResults(5); // Limit to first 5 records
		List<BankAccount> list = criteria.list();
		return list;
	}

//user want to get such a data whose balance is more than 1500
	public List<BankAccount> getAccountsBalanceMoreThan() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.add(Restrictions.gt("balance", 1500.00));
		criteria.add(Restrictions.between("balance", 1500d, 4000d));
		List<BankAccount> list = criteria.list();
		return list;
	}

}