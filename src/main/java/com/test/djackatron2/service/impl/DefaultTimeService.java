package com.test.djackatron2.service.impl;

import org.joda.time.LocalTime;

import com.test.djackatron2.service.TimeService;

public class DefaultTimeService implements TimeService {

	LocalTime openService;
	LocalTime closeService;
	@Override
	public void setOpenTime(LocalTime openService) {
		this.openService = openService;
	}

	@Override
	public void setCloseTime(LocalTime closeService) {
		this.closeService = closeService;
	}

	@Override
	public boolean isAvailiable(LocalTime transferTime) {
		return !(transferTime.isBefore(openService) || transferTime.isAfter(closeService));
	}

}
