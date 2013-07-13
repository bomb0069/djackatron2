package com.test.djackatron2.service;

import com.test.djackatron2.repository.AccountRepository;

public interface TransferService {

	void transfer(long transferFromAccountNo, long transferToAccountNo,
			double transferAmount);

	void setAccountRepository(AccountRepository accountRepo);

	void setFeePolicy(FeePolicy feePolicy);

}
