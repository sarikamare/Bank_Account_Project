 package com.tka.entity;

import javax.persistence.Entity;

import javax.persistence.Id;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
@Entity
public class BankAccount {
	@Id
	private String accountNumber;
	private String accountHolderName;
	private double balance;
	private String accountType;
	private String currency;
	
	public BankAccount() {
		
	}	
	public BankAccount(String accountNumber, String accountHolderName, double balance,String accountType, String currency) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.accountType=accountType;
		this.currency = currency;
	}



	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public java.lang.String toString() {
		return "BankAccount [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName + ", balance="
				+ balance + ", currency=" + currency + "]";
	}
	public void setAccountType1(String accountType2) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	

}
