package com.test.djackatron2.service;

import org.joda.time.LocalTime;

public interface TimeService {

	void setOpenTime(LocalTime openService);

	void setCloseTime(LocalTime closeService);

	boolean isAvailiable(LocalTime transferTime);

}
