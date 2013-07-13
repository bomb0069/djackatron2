package com.test.djackatron2.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.test.djackatron2.service.FeePolicy;
import com.test.djackatron2.service.impl.FlatFeePolicy;

@RunWith(value=Parameterized.class)
public class FlatFeePolicyTest {
	
	double fixRate;
	double amount;
	double transferFee;
	
	public FlatFeePolicyTest(double fixRate,	double amount, double transferFee ) {
		this.fixRate = fixRate;
		this.amount = amount;
		this.transferFee = transferFee;
	}
	
	@Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] {
				{ 5, 1, 5},
				{ 5, 10, 5},
				{ 5, 100, 5},
				{ 5, 1000, 5},
				{ 5, 10000, 5},
				{ 3, 1, 3},
				{ 3, 10, 3},
				{ 3, 100, 3},
				{ 3, 1000, 3},
				{ 3, 10000, 3}
		});
		
	}
	
	@Test
	public void testFixRatingTo5AndItWillGetThatWhenWeCalculateFee () {
		FeePolicy feePolicy = new FlatFeePolicy(fixRate);
		
		double tranferFee = feePolicy.calculateTranferRate(amount);
		
		assertThat(tranferFee,equalTo(fixRate)) ;
	}
}
