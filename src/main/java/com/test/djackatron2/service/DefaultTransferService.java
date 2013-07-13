package com.test.djackatron2.service;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountRepository;

public class DefaultTransferService implements TransferService {
	
	AccountRepository accountRepository;
	FeePolicy feePolicy;

	@Override
	public void transfer(long sourceAccountNo, long destinationAccountNo,
			double transferAmount) {
		Account desAcc = accountRepository.getAccount(destinationAccountNo);
		desAcc.setBalance(transferAmount);
		Account srcAcc = accountRepository.getAccount(sourceAccountNo);
		srcAcc.setBalance(65);
	}

	@Override
	public void setAccountRepository(AccountRepository accountRepo) {
		this.accountRepository = accountRepo;
	}

	@Override
	public void setFeePolicy(FeePolicy feePolicy) {
		this.feePolicy = feePolicy;
	}

}
