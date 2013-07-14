package com.test.djackatron2.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.test.djackatron2.model.Account;
import com.test.djackatron2.repository.AccountRepository;

public class AccountControllerTest {
	
	@Test
	public void testFindOne () {
		//given
		long accId = 1;
		
		Account account = new Account(accId,500);
		
		AccountRepository accountRepo = mock(AccountRepository.class);
		when(accountRepo.getAccount(accId)).thenReturn(account);

		AccountController controller = new AccountController();
		controller.setAccountRepository(accountRepo);
		
		//when
		Account controllAccount = controller.getOne(accId);
		
		//then
		assertThat(controllAccount.getId() ,equalTo(accId));
		verify(accountRepo).getAccount(accId);
	}
	
	@Test
	public void testSaveAccount () {
		//given
		long accId = 1;
		
		Account account = new Account(accId,500);
		
		AccountRepository accountRepo = mock(AccountRepository.class);
		when(accountRepo.create(account)).thenReturn(account);

		AccountController controller = new AccountController();
		controller.setAccountRepository(accountRepo);
		
		//when
		Account controllAccount = controller.save(account);
		
		//then
		assertThat(controllAccount.getId() ,equalTo(accId));
		verify(accountRepo).create(account);
	}

	@Test
	public void testUpdateAccount () {
		//given
		long accId = 1;
		
		Account account = new Account(accId,500);
		
		AccountRepository accountRepo = mock(AccountRepository.class);
		when(accountRepo.update(account)).thenReturn(account);

		AccountController controller = new AccountController();
		controller.setAccountRepository(accountRepo);
		
		//when
		Account controllAccount = controller.update(account);
		
		//then
		assertThat(controllAccount.getId() ,equalTo(accId));
		verify(accountRepo).update(account);
	}


	@Test
	public void testDeleteAccount () {
		//given
		long accId = 1;
		
		Account account = new Account(accId,500);
		
		AccountRepository accountRepo = mock(AccountRepository.class);
		when(accountRepo.delete(accId)).thenReturn(account);

		AccountController controller = new AccountController();
		controller.setAccountRepository(accountRepo);
		
		//when
		Account controllerAccount = controller.delete(accId);
		
		//then
		assertThat(controllerAccount.getId() ,equalTo(accId));
		verify(accountRepo).delete(accId);
	}
}
