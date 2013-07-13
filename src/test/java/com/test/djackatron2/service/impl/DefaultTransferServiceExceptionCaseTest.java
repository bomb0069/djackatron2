package com.test.djackatron2.service.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

import com.test.djackatron2.exception.InsufficiantFundException;
import com.test.djackatron2.exception.OutOfServiceException;
import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountRepository;
import com.test.djackatron2.service.FeePolicy;
import com.test.djackatron2.service.TimeService;
import com.test.djackatron2.service.TransferService;
import com.test.djackatron2.service.impl.DefaultTransferService;

public class DefaultTransferServiceExceptionCaseTest {
	
	TransferService transferService ;
	Account sourceAccount;
	Account destinationAccount;
	TimeService timeService;
	
	@Before
	public void setUp () {
		sourceAccount = new Account(1, 20);
		destinationAccount = new Account(2, 20);
		double transferFee = 5;
		
		AccountRepository accountRepo = mock(AccountRepository.class);
		FeePolicy feePolicy = mock(FeePolicy.class);
		timeService = mock(TimeService.class);
		
		when(feePolicy.calculateTranferRate(anyDouble())).thenReturn(transferFee);
		when(accountRepo.getAccount(sourceAccount.getAccountNo())).thenReturn(sourceAccount);
		when(accountRepo.getAccount(destinationAccount.getAccountNo())).thenReturn(destinationAccount);
		when(timeService.isAvailiable(any(LocalTime.class))).thenReturn(true);
		
		transferService = new DefaultTransferService();
		transferService.setAccountRepository(accountRepo);
		transferService.setFeePolicy(feePolicy);
		transferService.setTimeService(timeService);
			
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

	@Test(expected=OutOfServiceException.class)
	public void testExceptionWhenTimeServiceIsNotAvailiable() {
		double transferAmount = 20;
		
		when(timeService.isAvailiable(any(LocalTime.class))).thenReturn(false);

		transferService.transfer(sourceAccount.getAccountNo(),destinationAccount.getAccountNo(),transferAmount);
		
	}
}
