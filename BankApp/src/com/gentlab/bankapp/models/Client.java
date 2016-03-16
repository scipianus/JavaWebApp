package com.gentlab.bankapp.models;

import java.util.HashMap;

public class Client {
	static int totalClients;
	int clientID;
	String firstName, lastName;
	HashMap<Integer, BankAccount> accounts;

	public Client(String firstName, String lastName) {
		totalClients++;
		clientID = totalClients;
		this.firstName = firstName;
		this.lastName = lastName;
		accounts = new HashMap<>();
	}

	public int getID() {
		return this.clientID;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public HashMap<Integer, BankAccount> getAccounts() {
		return accounts;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public void addAccount(BankAccount account) {
		accounts.put(account.getID(), account);
	}

	public void updateAccount(BankAccount account) {
		accounts.put(account.getID(), account);
	}

	public void removeAccount(BankAccount account) {
		accounts.remove(account.getID());
	}
}
