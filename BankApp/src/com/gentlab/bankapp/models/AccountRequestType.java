package com.gentlab.bankapp.models;

public enum AccountRequestType {
	CREATE("create"), UPDATE("update"), DELETE("delete");

	private String text;

	AccountRequestType(String text) {
		this.text = text;
	}

	public static AccountRequestType fromString(String text) {
		if (text != null) {
			for (AccountRequestType type : AccountRequestType.values()) {
				if (text.equalsIgnoreCase(type.text)) {
					return type;
				}
			}
		}
		return null;
	}
}
