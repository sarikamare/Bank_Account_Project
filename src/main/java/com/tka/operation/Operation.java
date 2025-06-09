package com.tka.operation;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.tka.config.HibernateConfig;
import com.tka.entity.BankAccount;

import java.util.ArrayList;
import java.util.Arrays;
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

	public List<BankAccount> getAccountByInitial() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		System.out.println("Enter Initial");
		String initial = scanner.nextLine();
		criteria.add(Restrictions.like("accountHolderName", initial + "%"));
		List<BankAccount> list = criteria.list();
		session.close();
		return null;
	}

	public List<BankAccount>

			getSavingsAccountsWithBalanceIsMoreThan() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		System.out.println("Enter Acc Type");
		String acconutType = scanner.next();
		System.out.println("Enter Amount");
		double amount = scanner.nextDouble();
		SimpleExpression accType = (Restrictions.eq("accountType", "Savings"));
		SimpleExpression balance = (Restrictions.gt("balance", 1000d));
		criteria.add(Restrictions.and(accType, balance));
		List<BankAccount> list = criteria.list();
		return list;
	}

	// Projection

	// 1.Get All Records count
	public long totalAccountCount() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setProjection(Projections.rowCount());
		List list = criteria.list();

		long count = ((Number) list.get(0)).longValue();
		session.close(); // good practice to close session

		return count;
	}

	// 2.Get Minimum Balance

	public double minBalance() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setProjection(Projections.min("balance"));
		List list = criteria.list();

		double minimumBalance = ((Number) list.get(0)).doubleValue(); // correct casting
		session.close(); // always good to close session

		return minimumBalance;
	}

	// 3.Get Maximum Balance

	public double maxBalance() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setProjection(Projections.max("balance"));
		List list = criteria.list();

		double maxBalance = ((Number) list.get(0)).doubleValue();
		session.close();

		return maxBalance;
	}

	// 4.Get Average Balance

	public double avgBalance() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setProjection(Projections.avg("balance"));
		List list = criteria.list();

		double avgBalance = ((Number) list.get(0)).doubleValue();
		session.close();

		return avgBalance;
	}

	// 5.Get Sum Balance

	public double sumBalance() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setProjection(Projections.sum("balance"));
		List list = criteria.list();

		double sumBalance = ((Number) list.get(0)).doubleValue();
		session.close();

		return sumBalance;
	}

	// Restriction Task
	// 1.Get Accounts with High Balance (> 5000)

	public List<BankAccount> getAccountsWithHighBalance() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.add(Restrictions.gt("balance", 5000d));
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

//2.Get Non-Current Accounts

	public List<BankAccount> getNonCurrentAccounts() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.add(Restrictions.ne("accountType", "Current"));
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

	// 3.Get Accounts with NULL Currency

	public List<BankAccount> getAccountsWithNullCurrency() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.add(Restrictions.isNull("currency"));
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

	// 4.Get Accounts with Non-NULL Currency

	public List<BankAccount> getAccountsWithNonNullCurrency() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.add(Restrictions.isNotNull("currency"));
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

	// 5.Get Accounts where Currency is INR or USD

	public List<BankAccount> getAccountsByCurrencyList() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		List<String> currencyList = Arrays.asList("INR", "USD");
		criteria.add(Restrictions.in("currency", currencyList));
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

	// 6.Get Accounts where Currency is NOT INR or USD

	public List<BankAccount> getAccountsNotInCurrencyList() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		List<String> currencyList = Arrays.asList("INR", "USD");
		criteria.add(Restrictions.not(Restrictions.in("currency", currencyList)));
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

	// 7.Get Account Holder Names Between A and M
	public List<BankAccount> getNamesBetweenAandM() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.add(Restrictions.between("accountHolderName", "A", "M"));
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

}
