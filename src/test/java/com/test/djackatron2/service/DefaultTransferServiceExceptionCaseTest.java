package com.test.djackatron2.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyDouble;

import org.junit.Before;
import org.junit.Test;

import com.test.djackatron2.exception.InsufficiantFundException;
import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountRepository;

public class DefaultTransferServiceExceptionCaseTest {
	
	TransferService transferService ;
	Account sourceAccount;
	Account destinationAccount;
	
	@Before
	public void setUp () {
		sourceAccount = new Account(1, 20);
		destinationAccount = new Account(2, 20);
		double transferFee = 5;
		
		AccountRepository accountRepo = mock(AccountRepository.class);
		FeePolicy feePolicy = mock(FeePolicy.class);
		
		when(feePolicy.calculateTranferRate(anyDouble())).thenReturn(transferFee);
		when(accountRepo.getAccount(sourceAccount.getAccountNo())).thenReturn(sourceAccount);
		when(accountRepo.getAccount(destinationAccount.getAccountNo())).thenReturn(destinationAccount);
		
		transferService = new DefaultTransferService();
		transferService.setAccountRepository(accountRepo);
		transferService.setFeePolicy(feePolicy);
			
	}
	
	@Test(expected=InsufficiantFundException.class)
	public void testTransferInsufficientFund() {
		double transferAmount = 50;

		transferService.transfer(sourceAccount.getAccountNo(),destinationAccount.getAccountNo(),transferAmount);
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExceptionWhenZeroTransfer() {
		double transferAmount = 0;

		transferService.transfer(sourceAccount.getAccountNo(),destinationAccount.getAccountNo(),transferAmount);
		
	}


	@Test(expected=IllegalArgumentException.class)
	public void testExceptionWhenNegativeTransfer() {
		double transferAmount = -5;

		transferService.transfer(sourceAccount.getAccountNo(),destinationAccount.getAccountNo(),transferAmount);
		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExceptionWhenTransferLessThanMinimumTransfer() {
		double transferAmount = 20;
		transferService.setMinimumTransfer(50);

		transferService.transfer(sourceAccount.getAccountNo(),destinationAccount.getAccountNo(),transferAmount);
		
	}

}
