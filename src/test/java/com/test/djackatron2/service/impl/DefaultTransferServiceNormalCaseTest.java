package com.test.djackatron2.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountRepository;
import com.test.djackatron2.service.FeePolicy;
import com.test.djackatron2.service.TransferService;
import com.test.djackatron2.service.impl.DefaultTransferService;

@RunWith(value=Parameterized.class)
public class DefaultTransferServiceNormalCaseTest {

	Account sourceAccount;
	Account destinationAccount;
	double transferFee;
	double transferAmount;
	double sourceBalance;
	double destinationBalance;
	
	
	public DefaultTransferServiceNormalCaseTest(Account sourceAccount, Account destinationAccount,
			double transferFee,double transferAmount,	double sourceBalance,	double destinationBalance) {
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.transferFee = transferFee;
		this.transferAmount = transferAmount;
		this.sourceBalance = sourceBalance;
		this.destinationBalance = destinationBalance;
		
	}
	
	@Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] {
				{ new Account(1,100),new Account(2,0), 5, 30, 65, 30},
				{ new Account(1,100),new Account(2,0), 5, 40, 55, 40},
				{ new Account(1,200),new Account(2,0), 5, 40, 155, 40},
				{ new Account(1,200),new Account(2,20), 5, 40, 155, 60},
				{ new Account(1,100),new Account(2,0), 7, 40, 53, 40}
		});
		
	}
	
	
	@Test
	public void testTransferMoney () {
		
		AccountRepository accountRepo = mock(AccountRepository.class);
		FeePolicy feePolicy = mock(FeePolicy.class);
		
		when(feePolicy.calculateTranferRate(transferAmount)).thenReturn(transferFee);
		when(accountRepo.getAccount(sourceAccount.getAccountNo())).thenReturn(sourceAccount);
		when(accountRepo.getAccount(destinationAccount.getAccountNo())).thenReturn(destinationAccount);
		
		TransferService transferService = new DefaultTransferService();
		transferService.setAccountRepository(accountRepo);
		transferService.setFeePolicy(feePolicy);
			
		transferService.transfer(sourceAccount.getAccountNo(),destinationAccount.getAccountNo(),transferAmount);
		
		assertThat(destinationAccount.getBalance(),equalTo(destinationBalance));
		assertThat(sourceAccount.getBalance(),equalTo(sourceBalance));
		
	}

}
