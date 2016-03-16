package com.gentlab.bankappspring.model;

public enum RequestTypes {
	CREATE("create"), UPDATE("update"), DELETE("delete");

	private String text;

	RequestTypes(String text) {
		this.text = text;
	}

	public static RequestTypes fromString(String text) {
		if (text != null) {
			for (RequestTypes type : RequestTypes.values()) {
				if (text.equalsIgnoreCase(type.text)) {
					return type;
				}
			}
		}
		return null;
	}
}
