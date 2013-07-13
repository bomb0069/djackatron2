package com.test.djackatron2.model;

public class Account {

	private long accountNo ;
	private double balance ;
	
	public Account(long accountNo, int balance) {
		this.setAccountNo(accountNo);
		this.balance = balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

}
