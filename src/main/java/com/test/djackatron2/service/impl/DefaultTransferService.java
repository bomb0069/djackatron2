package com.test.djackatron2.service.impl;

import org.joda.time.LocalTime;

import com.test.djackatron2.exception.InsufficiantFundException;
import com.test.djackatron2.exception.OutOfServiceException;
import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountRepository;
import com.test.djackatron2.service.FeePolicy;
import com.test.djackatron2.service.TimeService;
import com.test.djackatron2.service.TransferService;

public class DefaultTransferService implements TransferService {
	
	AccountRepository accountRepository;
	FeePolicy feePolicy;
	TimeService timeService;
	
	private double minimumTransfer;

	@Override
	public void transfer(long sourceAccountNo, long destinationAccountNo,
			double transferAmount) {
		
		if (!timeService.isAvailiable(new LocalTime())) 
			throw new OutOfServiceException();
		
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

	@Override
	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}

}
