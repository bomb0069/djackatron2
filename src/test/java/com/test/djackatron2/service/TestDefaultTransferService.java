package com.test.djackatron2.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountRepository;

public class TestDefaultTransferService {
	
	@Test
	public void testTransferMoney () {
		long transferFromAccountNo = 1;
		long transferToAccountNo = 2;
		double transferAmount = 30;
		Account accountFrom = new Account(transferFromAccountNo,100);
		Account accountTo = new Account(transferToAccountNo,0);
		
		double expectedFromAccountBalance = 65;

		AccountRepository accountRepo = mock(AccountRepository.class);
		FeePolicy feePolicy = mock(FeePolicy.class);
		
		when(feePolicy.calculateTranferRate(transferAmount)).thenReturn(5d);
		when(accountRepo.getAccount(transferFromAccountNo)).thenReturn(accountFrom);
		when(accountRepo.getAccount(transferToAccountNo)).thenReturn(accountTo);
		
		TransferService transferService = new DefaultTransferService();
		transferService.setAccountRepository(accountRepo);
		transferService.setFeePolicy(feePolicy);
			
		transferService.transfer(transferFromAccountNo,transferToAccountNo,transferAmount);
		
		assertThat(accountTo.getBalance(),equalTo(transferAmount));
		assertThat(accountFrom.getBalance(),equalTo(expectedFromAccountBalance));
		
	}

}
