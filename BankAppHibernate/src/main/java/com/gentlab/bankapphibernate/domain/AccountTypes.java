package com.gentlab.bankapphibernate.domain;

public enum AccountTypes {
	CURRENT("CURRENT"), SAVINGS("SAVINGS");

	private String text;

	private AccountTypes(String text) {
		this.text = text;
	}

	public static AccountTypes fromString(String text) {
		if (text != null) {
			for (AccountTypes type : AccountTypes.values()) {
				if (text.equalsIgnoreCase(type.getText())) {
					return type;
				}
			}
		}
		return null;
	}

	public String getText() {
		return text;
	}

}
