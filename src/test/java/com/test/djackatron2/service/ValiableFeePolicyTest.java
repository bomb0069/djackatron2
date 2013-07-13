package com.test.djackatron2.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ValiableFeePolicyTest  {
	
	@Test 
	public void transferFeeWithZeroWhenWeTransfer1000 () {
		FeePolicy feePolicy = new ValiableFeePolicy();
		double transferFee = feePolicy.calculateTranferRate(500);
		assertThat(transferFee, equalTo(0d));
	}

	@Test 
	public void transferFeeWith10_01WhenWeTransfer1001 () {
		FeePolicy feePolicy = new ValiableFeePolicy();
		double transferFee = feePolicy.calculateTranferRate(1001);
		assertThat(transferFee, equalTo(10.01d));
	}

	@Test 
	public void transferFeeWith50WhenWeTransfer5000 () {
		FeePolicy feePolicy = new ValiableFeePolicy();
		double transferFee = feePolicy.calculateTranferRate(5000);
		assertThat(transferFee, equalTo(50d));
	}

	@Test 
	public void transferFeeWith10000WhenWeTransfer1000000 () {
		FeePolicy feePolicy = new ValiableFeePolicy();
		double transferFee = feePolicy.calculateTranferRate(1000000);
		assertThat(transferFee, equalTo(10000d));
	}

	@Test 
	public void transferFeeWith20000WhenWeTransferMoreThan1000000 () {
		FeePolicy feePolicy = new ValiableFeePolicy();
		double transferFee = feePolicy.calculateTranferRate(1000001);
		assertThat(transferFee, equalTo(20000d));
	}

	@Test 
	public void transferFeeWith20000WhenWeTransfer2000000 () {
		FeePolicy feePolicy = new ValiableFeePolicy();
		double transferFee = feePolicy.calculateTranferRate(2000000);
		assertThat(transferFee, equalTo(20000d));
	}
}
