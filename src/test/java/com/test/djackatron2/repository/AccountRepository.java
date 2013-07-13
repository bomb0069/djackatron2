package com.test.djackatron2.repository;

import com.test.djackatron2.model.Account;

public interface AccountRepository {

	public Account getAccount(long transferFromAccountNo);

}
