package com.gentlab.bankapp.models;

public class CurrentAccount extends BankAccount {
	private double value, commissionPercent;

	public CurrentAccount(double value, double commissionPercent) {
		super(BankAccountType.CURRENT_ACCOUNT);
		this.value = value;
		this.commissionPercent = commissionPercent;
	}

	public CurrentAccount(double value, double commissionPercent, int id) {
		super(BankAccountType.CURRENT_ACCOUNT, id);
		this.value = value;
		this.commissionPercent = commissionPercent;
	}

	public double getValue() {
		return this.value;
	}

	public double getCommissionPercent() {
		return this.commissionPercent;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void setCommissionPercent(double commissionPercent) {
		this.commissionPercent = commissionPercent;
	}
}
