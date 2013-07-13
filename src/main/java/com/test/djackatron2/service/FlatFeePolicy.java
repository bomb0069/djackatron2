package com.test.djackatron2.service;

public class FlatFeePolicy implements FeePolicy {

	private double transferFee ;
	
	public FlatFeePolicy(double transferFee) {
		this.transferFee = transferFee; 
	}

	@Override
	public double calculateTranferRate(double transferAmount) {
		return transferFee;
	}

}
