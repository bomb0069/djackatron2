package com.test.djackatron2.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalTime;
import org.junit.Test;

import com.test.djackatron2.service.TimeService;

public class DefaultTimeServiceTest {
	
	@Test
	public void testDefaultTeimeService () {
		LocalTime openService = new LocalTime(6,0);
		LocalTime closeService = new LocalTime(22,0);
		TimeService timeService = new DefaultTimeService();
		timeService.setOpenTime(openService);
		timeService.setCloseTime(closeService);
		
		assertTrue(timeService.isAvailiable(new LocalTime(21,0)));
		assertFalse(timeService.isAvailiable(new LocalTime(5,59)));
		assertTrue(timeService.isAvailiable(new LocalTime(6,0)));
		assertTrue(timeService.isAvailiable(new LocalTime(22,0)));
		assertFalse(timeService.isAvailiable(new LocalTime(22,1)));
		
	}

}
