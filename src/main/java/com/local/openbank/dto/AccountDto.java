package com.local.openbank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountDto {
	@JsonProperty(value = "user_id")
	private String userId;

	private String label;

	private String type;

	private BalanceDto balance;

	@JsonProperty(value = "branch_id")
	private String branchId;

	@JsonProperty(value = "account_routing")
	private AccountRoutingDto accountRouting;

}
