package com.gentlab.bankapphibernate.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNTS")
@DiscriminatorValue("SAVINGS")
public class SavingsAccount extends Account {
	@Column(name = "value")
	private double value;
	@Column(name = "interest_percent")
	private double interestPercent;

	public SavingsAccount() {
		setType(AccountTypes.SAVINGS);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getInterestPercent() {
		return interestPercent;
	}

	public void setInterestPercent(double interestPercent) {
		this.interestPercent = interestPercent;
	}
}
