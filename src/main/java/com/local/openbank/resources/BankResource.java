package com.local.openbank.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.local.openbank.model.Account;
import com.local.openbank.model.AccountBalances;
import com.local.openbank.model.Bank;
import com.local.openbank.service.IBankAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value="Bank Transcation API")
public class BankResource {

	@Autowired
	IBankAccountService bankAccountService;

	@GetMapping("/banks")
	@ApiOperation(value = "Get all the banks",response = List.class)
	public List<Bank> allBanks() {
		List<Bank> allBanks = bankAccountService.allBanks();
		log.info("Fetching branches for " + allBanks);
		return allBanks;
	}

	@GetMapping("/banks/{bank_id}/accounts/account_ids/private")
	@ApiOperation(value = "Get all the private accounts for the bank id",response = List.class)
	public List<Account> getPrivateBanks(@PathVariable(name = "bank_id") String bankId) {
		List<Account> allBanks = bankAccountService.getPrivateBanks(bankId);
		log.info("Fetching branches for " + allBanks);
		return allBanks;
	}

	@GetMapping("/banks/{bank_id}/balances")
	@ApiOperation(value = "Get all the private accounts balances for the bank id",response = List.class)
	public List<AccountBalances> bankAccountBalances(@PathVariable(name = "bank_id") String bankId) {
		List<AccountBalances> allBanks = bankAccountService.bankAccountBalances(bankId);
		log.info("Fetching branches for " + allBanks);
		return allBanks;
	}

	@PostMapping("/account/{account_id}")
	@ApiOperation(value = "Create a new account for the user",response = com.local.openbank.model.Account.class)
	public com.local.openbank.model.Account createAccount(@RequestBody com.local.openbank.dto.AccountDto accountDto,
			@PathVariable(name = "account_id") String accountId) {
		log.info("createAccount {}", accountDto);
		return bankAccountService.createAccount(accountDto, accountId);
	}
}
