package com.test.djackatron2.service.impl;

import com.test.djackatron2.exception.InsufficiantFundException;
import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountRepository;
import com.test.djackatron2.service.FeePolicy;
import com.test.djackatron2.service.TransferService;

public class DefaultTransferService implements TransferService {
	
	AccountRepository accountRepository;
	FeePolicy feePolicy;
	private double minimumTransfer;

	@Override
	public void transfer(long sourceAccountNo, long destinationAccountNo,
			double transferAmount) {
		if (transferAmount <= 0) 
			throw new IllegalArgumentException();
		else if (transferAmount < minimumTransfer)
			throw new IllegalArgumentException();
			
		Account srcAcc = accountRepository.getAccount(sourceAccountNo);
		Account desAcc = accountRepository.getAccount(destinationAccountNo);

		double totalWithdraw = transferAmount + feePolicy.calculateTranferRate(transferAmount);
		if (totalWithdraw > srcAcc.getBalance())
			throw new InsufficiantFundException();
		
		desAcc.setBalance(desAcc.getBalance() + transferAmount);
		srcAcc.setBalance(srcAcc.getBalance() - totalWithdraw);
	}

	@Override
	public void setAccountRepository(AccountRepository accountRepo) {
		this.accountRepository = accountRepo;
	}

	@Override
	public void setFeePolicy(FeePolicy feePolicy) {
		this.feePolicy = feePolicy;
	}

	@Override
	public void setMinimumTransfer(double minimumTransfer) {
		this.minimumTransfer = minimumTransfer;
		
	}

}
