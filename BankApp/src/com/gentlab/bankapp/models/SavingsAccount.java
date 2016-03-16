package com.gentlab.bankapp.models;

public class SavingsAccount extends BankAccount {
	private double value, interestPercent;

	public SavingsAccount(double value, double interestPercent) {
		super(BankAccountType.SAVINGS_ACCOUNT);
		this.value = value;
		this.interestPercent = interestPercent;
	}

	public SavingsAccount(double value, double interestPercent, int id) {
		super(BankAccountType.SAVINGS_ACCOUNT, id);
		this.value = value;
		this.interestPercent = interestPercent;
	}

	public double getValue() {
		return value;
	}

	public double getInterestPercent() {
		return interestPercent;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setInterestPercent(double interestPercent) {
		this.interestPercent = interestPercent;
	}

	public void refreshValue() {
		this.value += this.value * (this.interestPercent / 100.0);
	}
}
