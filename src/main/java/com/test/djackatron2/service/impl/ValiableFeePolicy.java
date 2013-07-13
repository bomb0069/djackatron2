package com.test.djackatron2.service.impl;

import com.test.djackatron2.service.FeePolicy;

public class ValiableFeePolicy implements FeePolicy {

	@Override
	public double calculateTranferRate(double tranferAmount) {
		if (tranferAmount <= 1000)
			return 0;
		else if (tranferAmount <= 1000000)
			return tranferAmount * 0.01;
		else 
			return 20000;
	}

}
