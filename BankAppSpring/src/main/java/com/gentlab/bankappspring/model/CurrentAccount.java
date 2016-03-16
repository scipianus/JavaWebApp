package com.gentlab.bankappspring.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNTS")
@DiscriminatorValue("CURRENT")
public class CurrentAccount extends Account {
	@Column(name = "value")
	private double value;
	@Column(name = "commission_percent")
	private double commissionPercent;

	public CurrentAccount() {
		setType(AccountTypes.CURRENT);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getCommissionPercent() {
		return commissionPercent;
	}

	public void setCommissionPercent(double commissionPercent) {
		this.commissionPercent = commissionPercent;
	}
}
