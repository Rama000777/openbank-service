package com.local.openbank.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Account {
    private String id;

    @JsonProperty("user_id")
    private String userId;

    private String label;

    @JsonProperty("bank_id")
    private String bankId;

    @JsonProperty("branch_id")
    private String branchId;
    
    private Balance balance;

    private String type;

    @JsonProperty("IBAN")
    private String iban;

    @JsonProperty("swist_bic")
    private String bic;

    @JsonProperty("account_routing")
    private AccountRouting accountRouting;

}
