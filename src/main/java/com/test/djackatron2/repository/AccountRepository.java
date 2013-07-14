package com.test.djackatron2.repository;

import com.test.djackatron2.model.Account;

public interface AccountRepository {

	public Account getAccount(long transferFromAccountNo);

	public Account create(Account account);

	public Account update(Account account);

	public Account delete(long accId);

}
