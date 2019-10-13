package com.local.openbank.service;

import java.util.List;

import com.local.openbank.model.Account;
import com.local.openbank.model.AccountBalances;
import com.local.openbank.model.Bank;

public interface IBankAccountService {

	List<Bank> allBanks();

	List<Account> getPrivateBanks(String bankId);

	List<AccountBalances> bankAccountBalances(String bankId);

	Account createAccount(com.local.openbank.dto.AccountDto accountDto, String accountId);

}
