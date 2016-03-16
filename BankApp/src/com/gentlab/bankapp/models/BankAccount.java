package com.gentlab.bankapp.models;

public class BankAccount {
	private static int totalAccounts;
	private int accountID;
	private BankAccountType accountType;

	public BankAccount() {
		accountType = null;
	}

	public BankAccount(BankAccountType accountType) {
		totalAccounts++;
		this.accountID = totalAccounts;
		this.accountType = accountType;
	}

	public BankAccount(BankAccountType accountType, int id) {
		totalAccounts = Math.max(totalAccounts, id);
		this.accountID = id;
		this.accountType = accountType;
	}

	public int getID() {
		return accountID;
	}

	public static int getNewID() {
		return totalAccounts + 1;
	}

	public BankAccountType getType() {
		return accountType;
	}

	public boolean isCurrentAccount() {
		return (accountType == BankAccountType.CURRENT_ACCOUNT);
	}
}
