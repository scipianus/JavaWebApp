package com.gentlab.bankapp.models;

public enum BankAccountType {
	CURRENT_ACCOUNT("current_account"), SAVINGS_ACCOUNT("savings_account");

	private String text;

	private BankAccountType(String text) {
		this.text = text;
	}

	public static BankAccountType fromString(String text) {
		if (text != null) {
			for (BankAccountType type : BankAccountType.values()) {
				if (text.equalsIgnoreCase(type.text)) {
					return type;
				}
			}
		}
		return null;
	}
}
