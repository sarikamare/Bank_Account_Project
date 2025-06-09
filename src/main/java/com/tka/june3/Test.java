package com.tka.june3;

import java.util.List;
import java.util.Scanner;

import com.tka.entity.BankAccount;
import com.tka.operation.Operation;

public class Test {

	public static void main(String[] args) {

		Operation operation = new Operation();
//		String msg=operation.createAccount();
//		System.out.println(msg);
//	}}

		Scanner scanner = new Scanner(System.in);
		int choice;
		boolean wantToContinue = true;

		do {
			System.out.println("1. Create Account");
			System.out.println("2. Fetch Account");
			System.out.println("3. Update Account");
			System.out.println("4. Delete Account");
			System.out.println("0. Stop Application");
			System.out.println("5. Get Accounts by account numbers");
			System.out.println("6. All Accounts");
			System.out.println("7. Get All Accounts in ASC Order");
			System.out.println("8. Get First Five");
			System.out.println("9. Get specific records starting from a particular number");
			System.out.println("10.Get All Accounts whose balance is more than 1500");
			System.out.println("11.Get All Accounts  Whose Holder Name Starts with?");
			System.out.println("12.Get All Accounts as paer type & whose balance is more ? ");
			System.out.println("13.Get All Records count");
			System.out.println("15. Get Minimum Balance");
			System.out.println("16. Get Maximum Balance");
			System.out.println("17. Get Average Balance");
			System.out.println("14. Get Sum Balance");
			System.out.println("18. Get Accounts with High Balance (> 5000)");
			System.out.println("19. Get Non-Current Accounts");
			System.out.println("20. Get Accounts with NULL Currency");
			System.out.println("21. Get Accounts with Non-NULL Currency");
			System.out.println("22. Get Accounts where Currency is INR or USD");
			System.out.println("23. Get Accounts where Currency is NOT INR or USD");
			System.out.println("24. Get Account Holder Names Between A and M");
			System.out.println("Enter any number from above to perform operation !!");

			choice = scanner.nextInt();
			scanner.nextLine(); // to consume leftover newline

			switch (choice) {
			case 1: {
				String msg = operation.createAccount();
				System.out.println(msg);
				break;
			}
			case 2: {
				String msg = operation.deleteAccount();
				System.out.println(msg);
				break;
			}
//			
			case 3: {
				String msg = operation.updateAccount();
				System.out.println(msg);
				break;
			}
			case 4: {
				String msg = operation.deleteAccount();
				System.out.println(msg);
				break;
			}
			case 0: {
				wantToContinue = false;
				break;
			}
			case 5: {
				List<BankAccount> accounts = operation.getBankAccountByGivenIds();
				for (BankAccount bankAccount : accounts) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 6: {
				List<BankAccount> allaccounts = operation.getAllBankAccounts();
				for (BankAccount bankAccount : allaccounts) {
					System.out.println(bankAccount);
				}
			}
			case 7: {
				List<BankAccount> list = operation.getAllBankAccountInASC();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 8: {
				List<BankAccount> list = operation.getFirstfiveAccounts();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 9: {
				List<BankAccount> list = operation.getSpecificRecord();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 10: {
				List<BankAccount> list = operation.getAccountsBalanceMoreThan();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 11: {
				List<BankAccount> list = operation.getAccountByInitial();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 12: {
				List<BankAccount> list = operation.getSavingsAccountsWithBalanceIsMoreThan();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 13: {
				long count = operation.totalAccountCount();
				{
					System.out.println(count);
				}
				break;
			}
			case 14: {
				double minimumBalance = operation.minBalance();
				{
					System.out.println(minimumBalance);
				}
				break;
			}
			case 15: {
				double minimumBalance = operation.maxBalance();
				{
					System.out.println(minimumBalance);
				}
				break;
			}

			case 16: {
				double avgBalance = operation.avgBalance();
				{
					System.out.println(avgBalance);
				}
				break;
			}

			case 17: {
				double sumBalance = operation.sumBalance();
				{
					System.out.println(sumBalance);
				}
				break;
			}
			case 18: {
				List<BankAccount> list = operation.getAccountsWithHighBalance();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 19: {
				List<BankAccount> list = operation.getNonCurrentAccounts();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 20: {
				List<BankAccount> list = operation.getAccountsWithNullCurrency();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 21: {
				List<BankAccount> list = operation.getAccountsWithNonNullCurrency();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 22: {
				List<BankAccount> list = operation.getAccountsByCurrencyList();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 23: {
				List<BankAccount> list = operation.getAccountsNotInCurrencyList();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 24: {
				List<BankAccount> list = operation.getNamesBetweenAandM();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}

			default: {
				System.out.println("Invalid choice! Application will now exit.");
				wantToContinue = false;
			}

				break;
			}

		} while (wantToContinue); // ✅ loop ends properly

		System.out.println("AppTerminated"); // ✅ outside loop
	}
}
