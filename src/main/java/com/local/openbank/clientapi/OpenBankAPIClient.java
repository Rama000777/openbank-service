package com.local.openbank.clientapi;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.local.openbank.dto.UserDto;
import com.local.openbank.model.Account;
import com.local.openbank.model.AccountBalances;
import com.local.openbank.model.Bank;
import com.local.openbank.model.Branch;
import com.local.openbank.model.User;

import lombok.Data;

@FeignClient(name = "openBankVersion", url = "${openbank.api.versionedUrl}")
public interface OpenBankAPIClient {

	@GetMapping(value = "banks", consumes = MediaType.APPLICATION_JSON_VALUE)
	Banks getBanks();

	@PostMapping(value = "users", consumes = MediaType.APPLICATION_JSON_VALUE)
	User createUser(@RequestBody UserDto user);

	@PutMapping("banks/{bankId}/accounts/{accountId}")
	Account createAccount(@PathVariable("bankId") String bankId, @PathVariable("accountId") String accountId,
			@RequestBody com.local.openbank.dto.AccountDto accountRequest);

	@GetMapping("banks/{bankId}/accounts/account_ids/private")
	List<Account> getAccounts(@PathVariable("bankId") String bankId);
	
	@GetMapping("banks/{bankId}/balances")
	List<AccountBalances> getAccountBalances(@PathVariable("bankId") String bankId);

	@Data
	class Banks {
		private List<Bank> banks;
	}

	@RequestMapping(method = RequestMethod.GET, value = "banks/{bankId}/branches")
    Branches getBranches(@PathVariable("bankId") String bankId);

	@Data
    class Branches {
        private List<Branch> branches;
    }
	
	@GetMapping("users/current")
    User getCurrentUser();
	
	@GetMapping(value = "my/banks/{bankId}/accounts/{accountId}/account")
    Account getAccount(@PathVariable("bankId") String bankId, @PathVariable("accountId") String accountId);
}
