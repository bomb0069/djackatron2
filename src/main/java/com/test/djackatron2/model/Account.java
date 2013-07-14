package com.test.djackatron2.model;

import org.joda.time.LocalTime;

public class Account {

	private long id ;
	private String name;
	private double balance ;
	private LocalTime expiredDate;
	
	public Account(long id, int balance) {
		this.setId(id);
		this.balance = balance;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalTime getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalTime expiredDate) {
		this.expiredDate = expiredDate;
	}

}
