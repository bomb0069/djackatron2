package com.test.djackatron2.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountRepository;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	private AccountRepository accountRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Account getOne(long accId) {
		return accountRepository.getAccount(accId);
	}

	public void setAccountRepository(AccountRepository accountRepo) {
		this.accountRepository = accountRepo;
	}

	public Account save(Account account) {
		return accountRepository.create(account);
	}

	public Account update(Account account) {
		return accountRepository.update(account);
	}

	public Account delete(long accId) {
		return accountRepository.delete(accId);
	}

}
