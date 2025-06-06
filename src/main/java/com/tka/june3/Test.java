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

			default: {
				System.out.println("Invalid choice! Application will now exit.");
				wantToContinue = false;
				break;
			}
			}

		} while (wantToContinue); // ✅ loop ends properly

		System.out.println("AppTerminated"); // ✅ outside loop
	}
}
