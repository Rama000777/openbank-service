package com.local.openbank.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.local.openbank.clientapi.OpenBankAPIClient;
import com.local.openbank.model.Account;
import com.local.openbank.model.AccountBalances;
import com.local.openbank.model.Bank;
import com.local.openbank.model.Branch;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BankAccountService implements IBankAccountService {
	@Autowired
	private OpenBankAPIClient openBankAPIClient;

	private static final String BANK_ID = "at02-1465--01";

	@Override
	public List<Bank> allBanks() {
		List<Bank> allBanks = openBankAPIClient.getBanks().getBanks();
		log.info("Fetching branches for " + allBanks);
		allBanks.stream().forEach(bank -> {
			try {
				List<Branch> branches = openBankAPIClient.getBranches(bank.getId()).getBranches();
				bank.setBranches(branches);
			} catch (Exception e) {
				bank.setBranches(Collections.<Branch>emptyList());
			}
		});
		log.info("Fetching branches for " + allBanks);
		return allBanks;
	}

	@Override
	public List<Account> getPrivateBanks(@PathVariable(name = "bank_id") String bankId) {
		List<Account> allBanks = openBankAPIClient.getAccounts(bankId);
		log.info("Fetching branches for " + allBanks);
		return allBanks;
	}

	@Override
	public List<AccountBalances> bankAccountBalances(@PathVariable(name = "bank_id") String bankId) {
		List<AccountBalances> allBanks = openBankAPIClient.getAccountBalances(bankId);
		log.info("Fetching branches for " + allBanks);
		return allBanks;
	}

	@Override
	public com.local.openbank.model.Account createAccount(@RequestBody com.local.openbank.dto.AccountDto accountDto,
			@PathVariable(name = "account_id") String accountId) {
		log.info("createAccount {}", accountDto);
		return openBankAPIClient.createAccount(BANK_ID, accountId, accountDto);
	}

}
