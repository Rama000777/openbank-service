package com.local.openbank.model;

import java.util.List;

import lombok.Data;

@Data
class Account_routings {
	private String scheme;
	private String address;
}

@Data
class Balance {
	private String currency;
	private String amount;
}

@Data
class Accounts {
	private String id;
	private String label;
	private String bank_id;
	private List<Account_routings> account_routings;
	private Balance balance;
}

@Data
class Overall_balance {
	private String currency;
	private String amount;
}

@Data
public class AccountBalances {
	private List<Accounts> accounts;

	private Overall_balance overall_balance;

	private String overall_balance_date;

	public void setAccounts(List<Accounts> accounts) {
		this.accounts = accounts;
	}

	public List<Accounts> getAccounts() {
		return this.accounts;
	}

	public void setOverall_balance(Overall_balance overall_balance) {
		this.overall_balance = overall_balance;
	}

	public Overall_balance getOverall_balance() {
		return this.overall_balance;
	}

	public void setOverall_balance_date(String overall_balance_date) {
		this.overall_balance_date = overall_balance_date;
	}

	public String getOverall_balance_date() {
		return this.overall_balance_date;
	}
}
