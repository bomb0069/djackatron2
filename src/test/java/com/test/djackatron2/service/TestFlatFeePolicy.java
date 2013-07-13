package com.test.djackatron2.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestFlatFeePolicy {
	
	@Test
	public void testFixRatingTo5AndItWillGetThatWhenWeCalculateFee () {
		double fixedTranferRate = 5;
		double transferAmount = 100;
		FeePolicy feePolicy = new FlatFeePolicy(fixedTranferRate);
		
		double tranferFee = feePolicy.calculateTranferRate(transferAmount);
		
		assertEquals(fixedTranferRate,tranferFee,0);

		transferAmount = 10;
		
		tranferFee = feePolicy.calculateTranferRate(transferAmount);
		
		assertEquals(fixedTranferRate,tranferFee,0);

		transferAmount = 1;
		
		tranferFee = feePolicy.calculateTranferRate(transferAmount);
		
		assertEquals(fixedTranferRate,tranferFee,0);
	}
}
